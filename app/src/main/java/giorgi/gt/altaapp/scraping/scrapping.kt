package giorgi.gt.altaapp.scraping

import android.os.Build
import androidx.annotation.RequiresApi
import giorgi.gt.altaapp.DATABASE.product_detailed_modal
import giorgi.gt.altaapp.DATABASE.product_standart_modal
import giorgi.gt.altaapp.DATABASE.specmodal
import org.jsoup.Jsoup
import java.util.function.Consumer


class scrapping {

    companion object{
        @RequiresApi(Build.VERSION_CODES.N)
        fun phone_category_pager(page:Int):ArrayList<product_standart_modal>{

            var link="https://alta.ge/smartphones-page-${page}.html"
            var doc=Jsoup.connect(link).timeout(0)
                .ignoreHttpErrors(true).get()
            return get_item_container(link)

        }

        @RequiresApi(Build.VERSION_CODES.N)
        fun split_category_pager(page:Int):ArrayList<product_standart_modal>{

            var link="https://alta.ge/air-conditioners-split-systems-page-${page}.html"
            var doc=Jsoup.connect(link).timeout(0)
                .ignoreHttpErrors(true).get()

return   get_item_container(link)


        }


        @RequiresApi(Build.VERSION_CODES.N)
        fun kitchen_category_pager(page:Int):ArrayList<product_standart_modal>{

            var link="https://alta.ge/home-appliances-page-${page}.html"
            var doc=Jsoup.connect(link).timeout(0)
                .ignoreHttpErrors(true).get()
           return get_item_container(link)

        }

        @RequiresApi(Build.VERSION_CODES.N)
        fun notebook_category_pager(page:Int):ArrayList<product_standart_modal>{


            var link="https://alta.ge/notebooks-page-${page}.html"
            var doc=Jsoup.connect(link).timeout(0)
                .ignoreHttpErrors(true).get()
          return  get_item_container(link)

        }

        @RequiresApi(Build.VERSION_CODES.N)
        fun getbanners() :ArrayList<String>{
        var data=ArrayList<String>()
            var link="https://alta.ge/"
            var doc=Jsoup.connect(link).get()
            var maincontainer=doc.select("div.span16")
                .select("div.banners")
                .select("div")
                .select("div")

            maincontainer.forEach(Consumer { it->

                for(i in it.getElementsByTag("div")){
                    var image=i.select("div.ty-banner__image-item").select("a")
                        .select("img").attr("src")
                        data.add(image
                        )



                }



            }

            )
        return data








        }





      @RequiresApi(Build.VERSION_CODES.N)
      fun get_item_container(url:String):ArrayList<product_standart_modal>{

          var data=ArrayList<product_standart_modal>()
            val doc  = Jsoup.connect(url).get()
            val maincontainer= (doc.select("div.grid-list"))

            for(i in maincontainer.select("div.ty-column3")){
                val d=i.select( "div.ty-grid-list__item")
                val m=i.select("div.ty-grid-list__price")
                val imagenav=d.select("div.ty-grid-list__image")
                val dateailed_linkk=d.select("div.ty-grid-list__item-name").select("a").attr("href")

                val n=m.select("span.ty-price")

                var    image = imagenav.select("img").attr("src")
                var  name=d.select("div.ty-grid-list__item-name").select("a").text()
                var  price=n.select("span.ty-price-num").text()


                var mainmodal=product_standart_modal(price,name,image,dateailed_linkk)
                data.add(mainmodal)


            }

      return data

      }
      @RequiresApi(Build.VERSION_CODES.N)
      fun item_detailed_view(url:String):product_detailed_modal{

            var product_images=ArrayList<String>()
            var specsdata=ArrayList<specmodal>()


            var doc =Jsoup.connect(url).timeout(0).get()
            var titlediv=doc.select("div.tygh-breadcrumbs")
            var main_div    =doc.select("div.ty-tygh")
                .select("div.ty-helper-container")
                .select("div.tygh-content")
                .select("div.container-fluid")
                .select("div.row-fluid").select("div.span16")

                . select("div.ty-product-bigpicture").
                select("div.ty-product-bigpicture__left").
                select("div.ty-product-bigpicture__left-wrapper")
                .select("div.ty-product-bigpicture__img")
                .select("div.ty-product-img")



          var price =doc.select("div.ty-tygh")
              .select("div.ty-helper-container")
              .select("div.tygh-content")
              .select("div.container-fluid")
              .select("div.row-fluid")
              .select("div.span16")
              . select("div.ty-product-bigpicture").
           select("div.ty-product-bigpicture__right")
              .select("div.prices-container")
              .select("div.ty-product-bigpicture__prices")
              .select("div.ty-product-block__price-actual")
              .select("span")
              .select("span.ty-price")
              .select("span.ty-price-num").text().toString()
















            main_div.forEach(Consumer { it->


                for(i in it.getElementsByTag("a")){
                    var image =(i.select("img").attr("src"))
                    product_images.add(image)

                }

            })



            var ad=titlediv.select("div.container-fluid").select("div")
            var aw= ad.select("div.ty-sidebox__body").select("div")

            var PRODUCT_TITLE=aw.select("div.nj_custom_product_title_product_page").select("div").select("span").text()
            var PRODUCT_ID=aw.select("div.nj_breadcrumbs_product_code").select("span").text()


            var  features_container=doc.select("div.ty-tygh")
                .select("div.ty-helper-container")
                .select("div.tygh-content")
                .select("div.container-fluid")
                .select("div.row-fluid").select("div.span16")
                .
                select("div.ty-product-bigpicture")
                .select("div.limited")

            features_container.forEach(Consumer{
                    it->
                for (i in it.getElementsByTag("div")){
                    for (c in i.select("div.ty-product-feature-group") ){

                        var Title=(c.select("h3").text())
                        var productfeature=c.select("div.ty-product-feature")
                        var product_key=productfeature.select("span.ty-product-feature__label").text()
                        var product_value=productfeature.select("div.ty-product-feature__value").text()
                        specsdata.add(specmodal(product_key,product_value))

                    }


                }

            })

            return product_detailed_modal(1,price.toString()  ,PRODUCT_TITLE, specsdata ,"Dw",
                product_images,PRODUCT_ID )


        }
        @RequiresApi(Build.VERSION_CODES.N)
        fun search(text:String):ArrayList<product_standart_modal> {
            var head="https://alta.ge/?subcats=Y&pcode_from_q=Y&pshort=Y&pfull=Y&pname=Y&pkeywords=Y&search_performed=Y&q="
            var tail="&dispatch=products.search"
            var final_link="${head}${text}${tail}"


            return get_item_container(final_link)

        }

}


    }






















