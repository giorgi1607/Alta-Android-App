package giorgi.gt.altaapp.paging

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import giorgi.gt.altaapp.DATABASE.product_standart_modal
import giorgi.gt.altaapp.scraping.scrapping
import java.io.IOException

class splitpagingsource :PagingSource<Int,product_standart_modal>() {

    var PAGE_SIZE=2
    var STARTING_PAGE=1


    override fun getRefreshKey(state: PagingState<Int, product_standart_modal>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, product_standart_modal> {
        var position=params.key ?:STARTING_PAGE
        return try{
            var response=scrapping.split_category_pager(position)
            var previospage=if(position==1){
                null
            }else{
                position-1
            }
            var nextpage=if(position==PAGE_SIZE){
                null
            }else{
                position+1
            }
            LoadResult.Page(response,previospage,nextpage)

        }catch (exception:IOException){
            LoadResult.Error(exception)
        }catch (exception:HttpException){
            LoadResult.Error(exception)
        }





    }
}