package com.example.simpleblog.mvc





import com.example.simpleblog.mvc.service.CacheService
import com.example.simpleblog.mvc.service.comment.CommentService
import com.example.simpleblog.mvc.setup.TestContainerSetup
import com.example.simpleblog.mvc.setup.TestRedisConfiguration
import com.example.simpleblog.mvc.web.dto.CommentSaveReq
import mu.KotlinLogging
import net.okihouse.autocomplete.repository.AutocompleteRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.context.annotation.Import
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.ScriptUtils
import org.springframework.test.context.TestConstructor
import javax.sql.DataSource


//, TestDataSource::class


@SpringBootTest(
    classes = [TestRedisConfiguration::class],
    properties = ["spring.profiles.active=test"]
)
//@ExtendWith(TestContainerSetup::class)
@Import(TestContainerSetup::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal class SimpleBlogModuleMvcApplicationTests(
    private val df: DefaultListableBeanFactory,
    private val commentService: CommentService,
    private val cacheManager: CacheManager,
    private val cacheService: CacheService,
    private val autocompleteRepository: AutocompleteRepository,
    private val dataSource: DataSource
)  {


    private val log = KotlinLogging.logger {  }


    @BeforeAll
    fun setupAll(){

        dataSource.connection.use { conn ->
            log.info("TEST!!!!!!!!!!!@@@@@@@=>${conn.metaData.url}")
            ScriptUtils.executeSqlScript(conn, ClassPathResource("/static/sql/testinit.sql"))
        }

    }


    @Test
    fun contextLoads() {

    }


    @Test
    fun testDataSourceTest(){

        log.info { dataSource.connection.metaData.url }

    }


    @Test
    fun cacheManagerTest(){

        cacheService.addAutoCompletePostTitle()

        cacheManager.cacheNames.map { cacheName->
            log.info { "cacheName==>$cacheName" }
            val caffeineCache = cacheManager.getCache(cacheName) as CaffeineCache
            caffeineCache.nativeCache
        }.forEach { cache->
            log.info { "????===>${cache.asMap().keys}" }
            cache.asMap().keys.forEach{
                log.info { """                   
                    key==> $it
                    value ==> ${cache.getIfPresent(it).toString()}                    
                """.trimIndent() }
            }

        }
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
