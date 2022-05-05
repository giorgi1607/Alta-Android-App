package giorgi.gt.altaapp.paging

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import giorgi.gt.altaapp.DATABASE.product_standart_modal
import giorgi.gt.altaapp.scraping.scrapping
import java.io.IOException

class notebookpagingsource :PagingSource<Int,product_standart_modal>() {

    var  PAGE_SIZE=8
    var STARTING_PAGE=1




    override fun getRefreshKey(state: PagingState<Int, product_standart_modal>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, product_standart_modal> {
        val position = params.key ?:  STARTING_PAGE

        return try {
            val response =scrapping.notebook_category_pager(position)
            val nextKey = if (position==PAGE_SIZE) {
                null
            } else {

                position + 1
            }
            LoadResult.Page(
                data = response,
                prevKey = if (position ==  STARTING_PAGE) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}