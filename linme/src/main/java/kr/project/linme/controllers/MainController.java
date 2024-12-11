package kr.project.linme.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import kr.project.linme.helpers.FileHelper;
import kr.project.linme.helpers.WebHelper;
import kr.project.linme.mappers.ProductMapper;
import kr.project.linme.models.Product;
import kr.project.linme.services.ProductService;

/**
 * 메인 페이지 컨트롤러
 */
@Controller
public class MainController {

    /**
     * 상품 서비스 객체 주입
     */
    @Autowired
    private ProductService productService;

    /**
     * WebHelper 주입
     */
    @Autowired
    private WebHelper webHelper;

    /**
     * FileHelper 주입
     */
    @Autowired
    private FileHelper fileHelper;

    /**
     * ProductMapper 주입
     */
    @Autowired
    private ProductMapper productMapper;

    /**
     * 메인 페이지
     * 
     * @param model
     * @return
     */
    @GetMapping("/main/main")
    public String main(Model model) {
        Product product = new Product();

        List<Product> vitaminProducts = null;
        List<Product> probioticProducts = null;
        List<Product> omegaProducts = null;
        List<Product> kidsProducts = null;
        List<Product> output = null;
        try {

            // 비타민 (카테고리 ID 2번)
            vitaminProducts = productService.getProductsByCategory(2);
            for (Product item : vitaminProducts) {
                item.setImg(fileHelper.getUrl(item.getImg()));
            }
            if (vitaminProducts.size() > 5) {
                vitaminProducts = vitaminProducts.subList(0, 5);
            }

            // 장건강 (카테고리 ID 3번)
            probioticProducts = productService.getProductsByCategory(3);
            for (Product item : probioticProducts) {
                item.setImg(fileHelper.getUrl(item.getImg()));
            }
            if (probioticProducts.size() > 5) {
                probioticProducts = probioticProducts.subList(0, 5);
            }

            // 오메가3 (카테고리 ID 4번)
            omegaProducts = productService.getProductsByCategory(4);
            for (Product item : omegaProducts) {
                item.setImg(fileHelper.getUrl(item.getImg()));
            }
            if (omegaProducts.size() > 5) {
                omegaProducts = omegaProducts.subList(0, 5);
            }

            // 아이영양제(카테고리 ID 1번)
            kidsProducts = productService.getProductsByCategory(1);
            for (Product item : kidsProducts) {
                item.setImg(fileHelper.getUrl(item.getImg()));
            }
            if (kidsProducts.size() > 5) {
                kidsProducts = kidsProducts.subList(0, 5);
            }

            // 전체 제품 목록
            output = productService.getList(product);
            for (Product item : output) {
                System.out.println("이미지 경로: " + item.getImg());
                item.setImg(fileHelper.getUrl(item.getImg()));
            }

        } catch (Exception e) {
            webHelper.serverError(e);
        }

        model.addAttribute("vitaminProducts", vitaminProducts);
        model.addAttribute("probioticProducts", probioticProducts);
        model.addAttribute("omegaProducts", omegaProducts);
        model.addAttribute("kidsProducts", kidsProducts);
        model.addAttribute("products", output);
        return "main/main";
    }

    /**
     * 카테고리 리스트 페이지
     * 
     * @param categoryId
     * @param model
     * @return
     */
    @GetMapping("/header/categoryList/{categoryId}")
    public String categoryList(@PathVariable("categoryId") int categoryId, Model model) {
        List<Product> products = new ArrayList<>();
        try {
            products = productService.getProductsByCategory(categoryId);
            if (products.isEmpty()) {
                model.addAttribute("noProducts", true);
            } else {
                model.addAttribute("noProducts", false);
                for (Product product : products) {
                    if (product.getImg() != null) {
                        product.setImg(fileHelper.getUrl(product.getImg()));
                    }
                }
                model.addAttribute("products", products);
            }
        } catch (Exception e) {
            webHelper.serverError(e.getMessage());
            e.printStackTrace();
        }
        return "header/category_list";
    }

    /**
     * 메인 페이지 => 아이콘 클릭시 해당 카테고리 상품 조회
     * 
     * @param categoryId 카테고리 ID
     * @param model      뷰에 데이터를 전달하기 위한 모델 객체
     * @return 카테고리별 상품 목록을 보여줄 뷰의 이름
     */
    @GetMapping("/main/main_ok/{categoryId}")
    public String listByCategory(@PathVariable("categoryId") int categoryId, Model model) {
        List<Product> products = null;
        try {
            // 카테고리 ID에 해당하는 제품 목록을 조회
            // 피로활력-4번(오메가3), 피부건강-2번(비타민), 장건강-3번(장건강), 면역력-1번(아이영양제)
            // 연결DB 없으면 화면에 메세지 출력 (해당상품이 존재하지 않습니다)
            products = productService.getProductsByCategory(categoryId);
            for (Product item : products) {
                // 각 제품의 이미지 URL을 설정
                item.setImg(fileHelper.getUrl(item.getImg()));
            }
        } catch (Exception e) {
            // 예외 발생 시 서버 에러 메시지를 출력
            webHelper.serverError(e);
        }

        // 조회된 제품 목록을 모델에 추가하여 main_ok뷰에 전달
        model.addAttribute("products", products);
        return "main/main_ok";
    }

    /**
     * 비타민 페이지
     * 
     * @param model
     * @return
     */
    @GetMapping("/main/list_no1")
    public String listNo1(Model model) {
        List<Product> vitaminProducts = null;
        try {
            vitaminProducts = productService.getProductsByCategory(2);
            for (Product item : vitaminProducts) {
                item.setImg(fileHelper.getUrl(item.getImg()));
            }
        } catch (Exception e) {
            webHelper.serverError(e);
        }

        model.addAttribute("products", vitaminProducts);
        return "main/list_no1";
    }

    /**
     * 장건강 페이지
     * 
     * @param model
     * @return
     */
    @GetMapping("/main/list_no2")
    public String listNo2(Model model) {
        List<Product> probioticProducts = null;
        try {
            probioticProducts = productService.getProductsByCategory(3);
            for (Product item : probioticProducts) {
                item.setImg(fileHelper.getUrl(item.getImg()));
            }
        } catch (Exception e) {
            webHelper.serverError(e);
        }

        model.addAttribute("products", probioticProducts);
        return "main/list_no2";
    }

    /**
     * 오메가3 페이지
     * 
     * @param model
     * @return
     */
    @GetMapping("/main/list_no3")
    public String listNo3(Model model) {
        List<Product> omegaProducts = null;
        try {
            omegaProducts = productService.getProductsByCategory(4);
            for (Product item : omegaProducts) {
                item.setImg(fileHelper.getUrl(item.getImg()));
            }
        } catch (Exception e) {
            webHelper.serverError(e);
        }

        model.addAttribute("products", omegaProducts);
        return "main/list_no3";
    }

    /**
     * 아이영양제 페이지
     * 
     * @param model
     * @return
     */
    @GetMapping("/main/list_no4")
    public String listNo4(Model model) {
        List<Product> kidsProducts = null;
        try {
            kidsProducts = productService.getProductsByCategory(1);
            for (Product item : kidsProducts) {
                item.setImg(fileHelper.getUrl(item.getImg()));
            }
        } catch (Exception e) {
            webHelper.serverError(e);
        }

        model.addAttribute("products", kidsProducts);
        return "main/list_no4";
    }

    /**
     * 신상품 페이지
     * 
     * @param model
     * @return
     */
    @GetMapping("/header/new")
    public String New(Model model) {
        Product product = new Product();
        List<Product> output = null;
        try {
            // 전체 제품 목록
            output = productService.getList(product);

            // 최신등록순으로 정렬
            output.sort((a, b) -> {
                if (a.getRegDate() == null && b.getRegDate() == null) {
                    return 0;
                } else if (a.getRegDate() == null) {
                    return 1;
                } else if (b.getRegDate() == null) {
                    return -1;
                } else {
                    return b.getRegDate().compareTo(a.getRegDate());
                }
            });

            // 제품 3개만 출력
            if (output.size() > 3) {
                // 마지막 3개만 출력(최신등록순)
                output = output.subList(output.size() - 3, output.size());
            }
            for (Product item : output) {
                System.out.println("이미지 경로: " + item.getImg());
                item.setImg(fileHelper.getUrl(item.getImg()));
            }
        } catch (Exception e) {
            webHelper.serverError(e);
        }
        model.addAttribute("products", output);
        return "header/new";
    }

    /**
     * 베스트 상품 페이지
     * 
     * @param model
     * @return
     */
    @GetMapping("/header/best")
    public String Best(Model model) {
        return "header/best";
    }

    /**
     * 
     * @param model
     * @return
     */
    @GetMapping("/header/spacial")
    public String Spacial(Model model) {
        return "header/spacial";
    }

    /**
     * 상세 페이지
     * 
     * @param productId
     * @param model
     * @return
     */
    @GetMapping("/view/view/{productId}")
    public String view(@PathVariable("productId") int productId, Model model) {
        Product product = null;
        try {
            product = productService.getProductById(productId);
            if (product != null) {
                // 이미지 경로 설정
                if (product.getImg() != null) {
                    product.setImg(fileHelper.getUrl(product.getImg()));
                }
                // 상세 이미지 경로 설정
                if (product.getDImg() != null) {
                    product.setDImg(fileHelper.getUrl(product.getDImg()));
                }
            }
        } catch (Exception e) {
            webHelper.serverError(e.getMessage());
            e.printStackTrace();
        }

        model.addAttribute("product", product);
        return "view/view";
    }

    /**
     * 검색바에서 검색시 이동할 페이지
     * 
     * @param keyword 검색 키워드
     * @param model   뷰에 데이터를 전달하기 위한 모델 객체
     * @return 검색 결과를 보여줄 뷰의 이름
     */
    @GetMapping("/main/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        // 데이터 조회
        // 검색 키워드를 이용하여 상품 목록을 조회
        List<Product> products = productMapper.searchProductsByKeyword(keyword);
        int count = products.size();

        // 이미지 URL 설정
        // 조회된 각 제품의 이미지 URL설정
        for (Product item : products) {
            item.setImg(fileHelper.getUrl(item.getImg()));
        }

        // 필요한 데이터 모델에 추가
        // 검색, 키워드, 검색결과 개수, 검색결과 리스트를 모델에 추가 => 뷰에 전달
        model.addAttribute("keyword", keyword); // 검색 키워드 추가
        model.addAttribute("categoryCount", count); // 검색 결과 개수 추가
        model.addAttribute("products", products); // 검색 결과 리스트 추가

        return "main/search";
    }
}