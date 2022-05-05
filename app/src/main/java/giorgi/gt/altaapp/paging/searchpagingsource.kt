package giorgi.gt.altaapp.paging

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import giorgi.gt.altaapp.DATABASE.product_standart_modal
import giorgi.gt.altaapp.scraping.scrapping
import java.io.IOException

class searchpagingsource(var link:String) :PagingSource<Int,product_standart_modal>() {

    var MAX_PAGE=1
    var Startint_page=1


    override fun getRefreshKey(state: PagingState<Int, product_standart_modal>): Int? {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, product_standart_modal> {
        var position=params.key ?:Startint_page

        return try {
            var nexpage=null
            var previospage=null
            val respone=scrapping.search(link)

            LoadResult.Page(respone,previospage,nexpage)




        }catch (exception:HttpException){
            LoadResult.Error(exception)
        }
        catch (exception:IOException){
            LoadResult.Error(exception)

        }

    }
}