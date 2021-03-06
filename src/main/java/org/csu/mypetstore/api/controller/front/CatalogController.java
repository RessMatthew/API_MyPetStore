package org.csu.mypetstore.api.controller.front;

import org.csu.mypetstore.api.entity.Item;
import org.csu.mypetstore.api.vo.ItemVO;
import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.entity.Category;
import org.csu.mypetstore.api.entity.Product;
import org.csu.mypetstore.api.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Date 2022/3/27 1:38 下午
 * @Author RessMatthew
 * @Version 1.0
 **/

@Controller
@RequestMapping("/catalog/")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("categories")
    @ResponseBody
    public CommonResponse<List<Category>> getCategoryList(){
        return catalogService.getCategoryList();
    }

    @GetMapping("categories/{id}")
    @ResponseBody
    public CommonResponse<Category> getCategoryById(@PathVariable("id")String categoryId){
        return catalogService.getCategoryById(categoryId);
    }

    @GetMapping("categories/{id}/products")
    @ResponseBody
    public CommonResponse<List<Product>> getProductListByCategoryId(@PathVariable("id")String categoryId){
        return catalogService.getProductListByCategoryId(categoryId);
    }

    @GetMapping("product/{id}")
    @ResponseBody
    public CommonResponse<Product> getProductById(@PathVariable("id")String productId){
        return catalogService.getProductById(productId);
    }

    @GetMapping("product/{id}/items")
    @ResponseBody
    public CommonResponse<List<ItemVO>> getItemListByProductId(@PathVariable("id")String productId){
        return catalogService.getItemListByProductId(productId);
    }

    @GetMapping("items/{id}")
    @ResponseBody
    public CommonResponse<ItemVO> getItemById(@PathVariable("id")String itemId){
        return catalogService.getItemById(itemId);
    }


    @GetMapping("searchThis")
    public void searchAutoComplete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyword;
        keyword=request.getParameter("keyword");
        Product product=new Product();
        Item item=new Item();
        List<Product> productList=new ArrayList<Product>();

        CommonResponse<List<Product>> res=catalogService.searchProductList(keyword);

        if(res.getMsg()!="未搜寻到结果"){
            productList = res.getData();
            StringBuffer sb = new StringBuffer("[");
            for(int i=0;i<productList.size();i++){
                if(i== productList.size()-1) {
                    sb.append("\"" + productList.get(i).getName() + "\"]");
                }else{
                    sb.append("\"" + productList.get(i).getName() + "\",");
                }
            }
            response.getWriter().write(sb.toString());
        }

    }




}
