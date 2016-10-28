package action;

import entity.ChargePoint;
import entity.CpInfo;
import entity.ProductInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.configureService;
import service.productService;
import service.chargeService;
import utils.Tools;

import javax.servlet.http.HttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ridiculous on 2016/5/27.
 */
@Controller
@RequestMapping("/Configure")
public class configureAction {

    @Resource
    private configureService configureService;

    @Resource
    private productService productService;

    @Resource
    private chargeService chargeService;


    @RequestMapping("addCpUser")
    @ResponseBody
    public String addCpUser(HttpServletRequest request) {
        String name = request.getParameter("name");
        String level = request.getParameter("level");
        String id = request.getParameter("id");
        String ip = request.getParameter("ip");
        if (name == null || id == null) {
            return "params error";
        } else if (level == null) {
            level = "2";
        }
        String secret = utils.Tools.genSecret(id);
        CpInfo cpInfo = new CpInfo(Integer.parseInt(id), name, new Byte(level), ip);
        configureService.addCpUser(cpInfo);
        return name;
    }
    @RequestMapping("updateCpUser")
    @ResponseBody
    public String updateCpUser(HttpServletRequest request) {
        String name = request.getParameter("name");
        String level = request.getParameter("level");
        String id = request.getParameter("id");
        String ip = request.getParameter("ip");
        if (name == null || id == null) {
            return "params error";
        } else if (level == null) {
            level = "2";
        }
        String secret = utils.Tools.genSecret(id);
        CpInfo cpInfo = new CpInfo(Integer.parseInt(id), name, new Byte(level), ip);
        configureService.updateCpUser(cpInfo);
        return name;
    }
    @RequestMapping("deleteCpUser")
    @ResponseBody
    public String deleteCpUser(HttpServletRequest request) {
        String id = request.getParameter("id");
        configureService.deleteCpUserById(Integer.parseInt(id));
        return id;
    }
    @RequestMapping("getProduct")
    @ResponseBody
    public String getProduct(HttpServletRequest request) {
        String id = request.getParameter("id");
        ProductInfo productInfo = productService.getProductByProductId(id);
        return productInfo.getProductName();
    }
    @RequestMapping("addProduct")
    @ResponseBody
    public String addProduct(HttpServletRequest request) {
        String spId = request.getParameter("spId");
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String price = request.getParameter("price");
        String description = request.getParameter("description");

        //String secret = Tools.genSecret(productId);
        ProductInfo productInfo = new ProductInfo(8,spId,productId,productName,price,description);
        productService.addProduct(productInfo);
        return productName;
    }
    @RequestMapping(value = "changePrivilege", method = {RequestMethod.POST})
    @ResponseBody
    public void changePrivilege(HttpServletRequest request) {
        CpInfo cpInfo = configureService.getUserbyId(1912);
        if (cpInfo.getLevel() == 1) {
            configureService.updatePrivilegebyId(1912, (byte) 2);
        }
        else {
            configureService.updatePrivilegebyId(1912, (byte) 1);
        }
    }
    @RequestMapping(value="addChargePoint")
    @ResponseBody
    public String addChargePoint(HttpServletRequest request) {
        String productId = request.getParameter("productId");
        String description = request.getParameter("description");

        String secret = Tools.genSecret(productId);
        ChargePoint chargePoint = new ChargePoint(1, productId, secret, description);
        chargeService.addChargePoint(chargePoint);
        return productId;
    }
    @RequestMapping("/index")
    public String index(HttpServletRequest request,Model model) throws IOException {
        List<CpInfo> cpInfoList = configureService.getAllCpInfos();
        model.addAttribute("cpinfos", cpInfoList);
        return "index";
    }
}
