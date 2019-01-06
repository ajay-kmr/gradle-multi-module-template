package com.example.admin.controller

import com.example.admin.AdminApplication
import com.example.admin.service.ProductService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = [AdminApplication.class])
class CatalogControllerSpec extends Specification {

    private ProductService productService = Mock(ProductService.class)

    @Subject
//    @Autowired
    ProductController productController = new ProductController(productService)

    /*https://allegro.tech/2018/04/Spring-WebMvcTest-with-Spock.html*/
//    https://gist.github.com/jeffsheets/ada3de8fe4a536e5351b
    //The magic happens here
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build()

    @LocalServerPort
    private int port

    ObjectMapper objectMapper = new ObjectMapper()

    @Unroll
    def "Test product search"() {
        given:
        Map request = [pageSize : 10,
                       pageIndex: 2]
        when:
        def response = this.mockMvc.perform(post("/v1/product/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andReturn().response
        then:
        response.status == HttpStatus.OK.value()
    }
}