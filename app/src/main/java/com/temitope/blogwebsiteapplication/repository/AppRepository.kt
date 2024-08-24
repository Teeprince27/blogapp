
import com.temitope.blogwebsiteapplication.api.BlogCloud

class AppRepository {

    suspend fun getPost() =
        BlogCloud.blogAPI.getPost()
}
