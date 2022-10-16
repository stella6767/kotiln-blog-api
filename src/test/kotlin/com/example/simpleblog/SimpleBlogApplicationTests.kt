package com.example.simpleblog

import com.example.simpleblog.domain.commenet.CommentSaveReq
import com.example.simpleblog.service.CommentService
import com.example.simpleblog.setup.TestRedisConfiguration
import net.okihouse.autocomplete.repository.AutocompleteRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles


@ActiveProfiles("test")
@SpringBootTest(classes = [TestRedisConfiguration::class])
class SimpleBlogApplicationTests(
    @Autowired
    val df:DefaultListableBeanFactory
) {

    @Autowired
    private lateinit var commentService: CommentService

    @Autowired
    private lateinit var autocompleteRepository: AutocompleteRepository


    @Test
    fun contextLoads() {
    }


    @Test
    fun autoCompleteTest(){

        val apple = "apple"
//        // step1. clear a "apple"
//        autocompleteRepository.clear(apple)
        // step2. Add a "apple"
        autocompleteRepository.add(apple)
        // step3. Get auto-complete words with prefix "a"
        val autocompletes = autocompleteRepository.complete("a")

        Assertions.assertTrue(autocompletes.size == 1)
        val autocompleteData = autocompletes[0]
        Assertions.assertTrue(autocompleteData.value == apple)
        Assertions.assertTrue(autocompleteData.score == 1)
    }

    @Test
    fun saveCommentTest(){

        val dto = CommentSaveReq(
            memberId = 2,
            content = "test content5",
            postId = 1,
            idAncestor = 3
        )

        commentService.saveComment(dto)

    }



    @Test
    fun printBeanNames(){

        val names = df.beanDefinitionNames

        for (name in names) {
            val bean = df.getBean(name)
            println("name = $name  object = $bean ")
        }

    }


}
