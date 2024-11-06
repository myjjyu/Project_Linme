/*****************************************************/
/* 메인영역 자바스크립트
/*****************************************************/
const contentData = [
  {
    menu: "내 몸의 활력을 위한 멀티 비타민",
    title: "💪비타민 충전타임💪",
    products: [
      {
        image: "../../static/assets/img/main/비타민충전타임1.jpg",
        productName: "네추럴메이드 멀티비타민 앤 미네랄",
        discountRate: "45",
        originalPrice: "36,000",
        salePrice: "19,800",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/비타민충전타임2.jpg",
        productName: "[앙팡] 다이노 비타민C (500정)",
        discountRate: "9",
        originalPrice: "30,000",
        salePrice: "27,500",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/비타민충전타임3.jpg",
        productName: "[조아제약] 활력비타민B 콤플렉스 850mgX60정",
        discountRate: "31",
        originalPrice: "55,900",
        salePrice: "38,000",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/비타민충전타임4.jpg",
        productName: "[광동] 비타500 데일리스틱 (30포)",
        discountRate: "5",
        originalPrice: "12,900",
        salePrice: "12,300",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/비타민충전타임5.jpg",
        productName: "네추럴메이드 멀티비타민 앤 미네랄",
        discountRate: "36",
        originalPrice: "19,900",
        salePrice: "12,900",
        shippingInfo: "무료배송",
      },
    ],
  },
  {
    menu: "유산균으로 장 편한 하루를 보내보세요",
    title: "💊온 가족 장 건강 케어💊",
    products: [
      {
        image: "../../static/assets/img/main/장건강케어1.jpg",
        productName: "헬시알엔 플로라이뮨 100억 보장 생유산균 350mg x 30캡슐",
        discountRate: "29",
        originalPrice: "26,000",
        salePrice: "18,700",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/장건강케어2.jpg",
        productName: "[코오롱제약] 로얄메디생유산균 2gX30포 (단품)",
        discountRate: "22",
        originalPrice: "35,000",
        salePrice: "27,300",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/장건강케어3.jpg",
        productName: "[조아제약] 데일리 신바이오틱스 플러스 6gX60포",
        discountRate: "46",
        originalPrice: "70,000",
        salePrice: "38,400",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/장건강케어4.jpg",
        productName: "[김오곤원장] 한방 다이어트 유산균 3.5gX90포",
        discountRate: "46",
        originalPrice: "120,000",
        salePrice: "65,000",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/장건강케어5.jpg",
        productName: "헬시알엔 퍼펙트 혈당 앤 유산균 3g x 30포",
        discountRate: "24",
        originalPrice: "20,000",
        salePrice: "15,200",
        shippingInfo: "무료배송",
      },
    ],
  },
  {
    menu: "건강한 혈행을 위해 필요한 영양소",
    title: "✔️대체불가 필수 지방산 오메가-3✔️",
    products: [
      {
        image: "../../static/assets/img/main/건강한혈행1.jpg",
        productName:
          "(캐나다/3개월) 네츄럴메이드 플랙씨드앤햄프씨드오일 (식물성오메가)1000mg 180캡슐",
        discountRate: "58",
        originalPrice: "87,000",
        salePrice: "36,800",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/건강한혈행2.png",
        productName: "[코오롱제약] 알티지오메가3 멀티플러스 1,023mgX30캡슐",
        discountRate: "55",
        originalPrice: "70,000",
        salePrice: "32,000",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/건강한혈행3.jpg",
        productName: "네추럴메이드 알티지 오메가-3 에이드 120C(2개월분)",
        discountRate: "75",
        originalPrice: "79,600",
        salePrice: "20,100",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/건강한혈행4.jpg",
        productName: "[퍼니트] 프리미엄 식물성 알티지 오메가3 880mgX30캡슐",
        discountRate: "49",
        originalPrice: "27,900",
        salePrice: "14,500",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/건강한혈행5.jpg",
        productName:
          "[비욘드오리진] 프라임 알티지 오메가-3 비타민D,E 1,350mgX60정",
        discountRate: "5",
        originalPrice: "75,000",
        salePrice: "71,250",
        shippingInfo: "무료배송",
      },
    ],
  },
  {
    menu: "성장기 우리 아이 맛있게 영양 보충 해주세요.",
    title: "👧우리 아이를 위한 영양 보충제👦",
    products: [
      {
        image: "../../static/assets/img/main/영양보충1.png",
        productName:
          "카카홀릭 키즈 멀티바이오자임 효소 초코맛 어린이 키즈 아기 유아",
        discountRate: "17",
        originalPrice: "30,000",
        salePrice: "24,900",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/비타민충전타임4.jpg",
        productName: "[광동] 비타500 데일리스틱 (30포)",
        discountRate: "5",
        originalPrice: "12,900",
        salePrice: "12,300",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/영양보충2.png",
        productName: "아이튼 퓨어 프로폴리스 젤리 어린이 키즈 아기 유아",
        discountRate: "34",
        originalPrice: "30,000",
        salePrice: "19,900",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/비타민충전타임2.jpg",
        productName: "[앙팡] 다이노 비타민C (500정)",
        discountRate: "9",
        originalPrice: "30,000",
        salePrice: "27,500",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/영양보충5.png",
        productName: "비오즌디 마스터바이오틱스 어린이 키즈 아기 유아 유산균",
        discountRate: "12",
        originalPrice: "45,000",
        salePrice: "39,800",
        shippingInfo: "무료배송",
      },
    ],
  },
  {
    menu: "내 몸의 기둥을 지켜라!",
    title: "🦴뼈 튼튼, 관절 튼튼👌",
    products: [
      {
        image: "../../static/assets/img/main/뼈튼튼1.jpg",
        productName: "헬시알엔 MBP 앤 콘드로이친 1200 600mg x 60정",
        discountRate: "32",
        originalPrice: "19,000",
        salePrice: "13,000",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/뼈튼튼2.jpg",
        productName: "네추럴메이드 MSM 조인트플렉스",
        discountRate: "45",
        originalPrice: "36,000",
        salePrice: "19,800",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/뼈튼튼3.jpg",
        productName:
          "[대원제약] 관절엔 상어연골 뮤코다당 단백 콘드로이친 플러스 1,200mgX60정",
        discountRate: "16",
        originalPrice: "23,500",
        salePrice: "19,800",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/뼈튼튼4.jpg",
        productName: "[코오롱제약] MSM 파워 1500 1,000mgX60정",
        discountRate: "69",
        originalPrice: "100,000",
        salePrice: "31,000",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/뼈튼튼5.jpg",
        productName: "[광동] 맑은365 칼마디(칼슘마그네슘비타민D) 120정",
        discountRate: "19",
        originalPrice: "66,000",
        salePrice: "53,800",
        shippingInfo: "무료배송",
      },
    ],
  },
  {
    menu: "환절기 대비 영양제 모음",
    title: "😷환절기 미리 준비하기😷",
    products: [
      {
        image: "../../static/assets/img/main/환절기대비1.jpg",
        productName: "[코오롱제약] 기억력혈행엔 징코플러스 500mgX30정",
        discountRate: "43",
        originalPrice: "20,000",
        salePrice: "11,400",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/환절기대비2.jpg",
        productName: "[광동] 맑은365 그린프로폴리스(스프레이) 30ml",
        discountRate: "25",
        originalPrice: "19,800",
        salePrice: "15,000",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/환절기대비3.jpg",
        productName: "네추럴메이드 징코큐텐(2개월분)",
        discountRate: "73",
        originalPrice: "72,500",
        salePrice: "19,800",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/환절기대비4.jpg",
        productName: "[코오롱제약] MSM 파워 1500 1,000mgX60정",
        discountRate: "69",
        originalPrice: "100,000",
        salePrice: "31,000",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/환절기대비5.jpg",
        productName: "[광동] 맑은365 면역보감 환 3.75gX30환",
        discountRate: "50",
        originalPrice: "145,000",
        salePrice: "73,000",
        shippingInfo: "무료배송",
      },
    ],
  },
  {
    menu: "뇌의 건강이 곧 심신의 건강!",
    title: "✨뇌를 깨워주세요✨",
    products: [
      {
        image: "../../static/assets/img/main/뇌건강1.jpg",
        productName:
          "(캐나다/1개월분) 네츄럴메이드 신제품-두뇌건강지킴이 포스파티딜세린 브레인스톰 1200mg",
        discountRate: "50",
        originalPrice: "59,000",
        salePrice: "29,800",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/뇌건강2.jpg",
        productName:
          "헬시알엔 두뇌기억력+인지력케어 포스파티딜세린 징코 브레인 450mg x 30캡슐",
        discountRate: "43",
        originalPrice: "27,000",
        salePrice: "15,400",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/뇌건강3.jpg",
        productName:
          "[퍼니트] 인지력개선 브레인액티브 포스파티딜세린 700mgX30캡슐",
        discountRate: "27",
        originalPrice: "29,900",
        salePrice: "21,900",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/뇌건강4.jpg",
        productName:
          "[보령컨슈머헬스케어] 보령 기억력 혈행개선엔 징코 프리미엄100 500mgX100정",
        discountRate: "14",
        originalPrice: "48,000",
        salePrice: "41,500",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/장건강케어5.jpg",
        productName: "[코오롱제약] 기억력혈행엔 징코플러스 500mgX30정",
        discountRate: "43",
        originalPrice: "20,000",
        salePrice: "11,400",
        shippingInfo: "무료배송",
      },
    ],
  },
  {
    menu: "혈당, 혈관, 혈행. 피도 관리가 필요해요",
    title: "🩸혈(血)의 중요성🩸",
    products: [
      {
        image: "../../static/assets/img/main/뇌건강2.jpg",
        productName:
          "헬시알엔 두뇌기억력+인지력케어 포스파티딜세린 징코 브레인 450mg x 30캡슐",
        discountRate: "43",
        originalPrice: "27,000",
        salePrice: "15,400",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/혈행관리2.jpg",
        productName: "[퍼니트] 코큐텐 혈압케어 플러스 500mgX60캡슐",
        discountRate: "39",
        originalPrice: "25,900",
        salePrice: "15,900",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/건강한혈행3.jpg",
        productName: "네추럴메이드 알티지 오메가-3 에이드 120C(2개월분)",
        discountRate: "75",
        originalPrice: "79,600",
        salePrice: "20,100",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/건강한혈행5.jpg",
        productName:
          "[비욘드오리진] 프라임 알티지 오메가-3 비타민D,E 1,350mgX60정",
        discountRate: "5",
        originalPrice: "75,000",
        salePrice: "71,250",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/건강한혈행4.jpg",
        productName: "[퍼니트] 프리미엄 식물성 알티지 오메가3 880mgX30캡슐",
        discountRate: "49",
        originalPrice: "27,900",
        salePrice: "14,500",
        shippingInfo: "무료배송",
      },
    ],
  },
  {
    menu: "눈 건강을 위해 루테인 상품을 추천드려요!",
    title: "👀피로한 눈을 위한 솔루션👀",
    products: [
      {
        image: "../../static/assets/img/main/눈건강1.jpg",
        productName: "네추럴메이드 울트라파워 아이 루테인(3개월분)",
        discountRate: "46",
        originalPrice: "32,500",
        salePrice: "17,800",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/눈건강2.jpg",
        productName:
          "[보령컨슈머헬스케어] 보령 눈건강 루테인지아잔틴 500mgX60캡슐",
        discountRate: "34",
        originalPrice: "79,000",
        salePrice: "52,400",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/눈건강3.jpg",
        productName: "[광동] 맑은365 눈건강 루테인11 60캡슐",
        discountRate: "28",
        originalPrice: "66,000",
        salePrice: "48,000",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/눈건강4.jpg",
        productName: "네추럴메이드 울트라파워 아이 루테인 선물세트 (6개월분)",
        discountRate: "50",
        originalPrice: "65,000",
        salePrice: "32,500",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/눈건강5.jpg",
        productName:
          "[보령컨슈머헬스케어] 보령 눈건강 루테인 500mgX30캡슐X3박스",
        discountRate: "33",
        originalPrice: "79,000",
        salePrice: "53,400",
        shippingInfo: "무료배송",
      },
    ],
  },
  {
    menu: "건강한 아름다움을 위한 이너뷰티 추천템",
    title: "🙌오늘이 가장 젊은 날🙌",
    products: [
      {
        image: "../../static/assets/img/main/이너뷰티1.jpg",
        productName: "[퍼니트] 프리미엄 유기농 비오틴 플러스 400mgX60정",
        discountRate: "31",
        originalPrice: "25,900",
        salePrice: "17,900",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/이너뷰티2.jpg",
        productName: "[퍼니트] 새빨간 콜레우스포스콜리 800mgX56정",
        discountRate: "34",
        originalPrice: "29,900",
        salePrice: "19,800",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/이너뷰티3.jpg",
        productName: "헬시알엔 슈프림 슬림핏 다이어트 1000mg x 120정",
        discountRate: "32",
        originalPrice: "34,000",
        salePrice: "23,300",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/이너뷰티4.jpg",
        productName: "[퍼니트] 카무트 골드핏 발효효소 3gX30포",
        discountRate: "15",
        originalPrice: "27,900",
        salePrice: "23,900",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/이너뷰티5.jpg",
        productName: "[코오롱제약] 앱솔루트 저분자피쉬콜라겐 2gX30포 (원통)",
        discountRate: "62",
        originalPrice: "100,000",
        salePrice: "38,000",
        shippingInfo: "무료배송",
      },
    ],
  },
  {
    menu: "남성 활력 증진에 필요한 영양제 모음",
    title: "남성 영양제 추천📣",
    products: [
      {
        image: "../../static/assets/img/main/남성영양제1.jpg",
        productName: "[퍼니트] 프리미엄 유기농 밀크씨슬 1,000mgX30정",
        discountRate: "44",
        originalPrice: "29,000",
        salePrice: "16,900",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/남성영양제2.jpg",
        productName: "헬시알엔 블랙마카르지닌 4X 파워 1000mg x 120정",
        discountRate: "47",
        originalPrice: "45,000",
        salePrice: "24,000",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/남성영양제3.png",
        productName: "[코오롱제약] 쏘팔메토 옥타코사놀 파워업 1,000mgX30캡슐",
        discountRate: "71",
        originalPrice: "100,000",
        salePrice: "29,000",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/남성영양제4.jpg",
        productName: "[비욘드오리진] 맥주효모비오틴 플러스 800mgX120정",
        discountRate: "7",
        originalPrice: "48,000",
        salePrice: "45,090",
        shippingInfo: "무료배송",
      },
      {
        image: "../../static/assets/img/main/환절기대비5.jpg",
        productName: "[광동] 맑은365 면역보감 환 3.75gX30환",
        discountRate: "50",
        originalPrice: "145,000",
        salePrice: "73,000",
        shippingInfo: "무료배송",
      },
    ],
  },
];

const productContainer = document.getElementById("productContainer");

// 반복문으로 contents 생성
contentData.forEach((content) => {
  const contentsDiv = document.createElement("div");
  contentsDiv.classList.add("contents");

  // headerMenu 추가
  const headerMenu = document.createElement("div");
  headerMenu.classList.add("headerMenu");
  headerMenu.textContent = content.menu;

  // headerMenutitle 추가
  const headerMenutitle = document.createElement("div");
  headerMenutitle.classList.add("headerMenutitle");
  headerMenutitle.innerHTML = content.title;

  const buttonLink = document.createElement("a");
  buttonLink.href = "otherPage.html";
  const button = document.createElement("button");
  button.type = "button";
  const img = document.createElement("img");
  img.src = "../../static/assets/img/main/arrow_showMore.png";
  img.alt = "arrow_showMore";
  button.appendChild(img);
  buttonLink.appendChild(button);
  headerMenutitle.appendChild(buttonLink);

  contentsDiv.appendChild(headerMenu);
  contentsDiv.appendChild(headerMenutitle);

  // headerMenutitleimg 생성
  const headerMenutitleimg = document.createElement("div");
  headerMenutitleimg.classList.add("headerMenutitleimg");

  // 각 제품 추가
  content.products.forEach((product) => {
    const boximg = document.createElement("div");
    boximg.classList.add("boximg");

    const link = document.createElement("a");
    link.href = "./상세페이지"; // 상세 페이지로의 링크 설정

    const productImg = document.createElement("img");
    productImg.src = product.image; // 이미지 경로
    productImg.alt = product.productName; // 제품명으로 대체
    productImg.classList.add("imgbox");

    const productName = document.createElement("p");
    productName.classList.add("productName");
    productName.textContent = product.productName; // 제품 이름으로 수정

    const priceContainer = document.createElement("div"); // 할인율이랑 원가랑 한줄에 묶기
    priceContainer.classList.add("priceContainer");

    const discountRate = document.createElement("p");
    discountRate.classList.add("discountRate");
    discountRate.textContent = `${product.discountRate}%`; // 할인율 표시

    const originalPrice = document.createElement("p");
    originalPrice.classList.add("originalPrice");
    originalPrice.textContent = `${product.originalPrice}원`; // 원가 표시

    const salePrice = document.createElement("p");
    salePrice.classList.add("salePrice");
    salePrice.textContent = `${product.salePrice}원`; // 판매가 표시

    // 할인가와 원래 가격을 priceContainer에 추가
    priceContainer.append(discountRate, originalPrice);

    const shippingInfo = document.createElement("p");
    shippingInfo.classList.add("shippingInfo");
    shippingInfo.textContent = product.shippingInfo; // 배송 정보

    link.append(
      productImg,
      productName,
      priceContainer,
      salePrice,
      shippingInfo
    );
    boximg.appendChild(link);
    headerMenutitleimg.appendChild(boximg);
  });

  contentsDiv.appendChild(headerMenutitleimg);
  productContainer.appendChild(contentsDiv);
});
