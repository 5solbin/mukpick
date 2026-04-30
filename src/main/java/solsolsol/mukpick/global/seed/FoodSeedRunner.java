package solsolsol.mukpick.global.seed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import solsolsol.mukpick.domain.food.entity.Food;
import solsolsol.mukpick.domain.food.entity.Ingredient;
import solsolsol.mukpick.domain.food.entity.RecipeStep;
import solsolsol.mukpick.domain.food.repository.FoodRepository;

import java.util.List;

import static solsolsol.mukpick.domain.food.entity.Food.CuisineType.*;
import static solsolsol.mukpick.domain.food.entity.Food.SpicyLevel.*;
import static solsolsol.mukpick.domain.food.entity.Food.FoodType.*;
import static solsolsol.mukpick.domain.food.entity.Food.TempType.*;

@Slf4j
@Component
@Profile("!test")
@RequiredArgsConstructor
public class FoodSeedRunner implements CommandLineRunner {

    private final FoodRepository foodRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (foodRepository.count() > 0) {
            log.info("[Seed] 이미 음식 데이터가 존재합니다. 건너뜁니다.");
            return;
        }

        log.info("[Seed] 음식 데이터 시드 시작...");

        List<Food> foods = List.of(

            // ── 한식 / 매운 / 밥 / 뜨거운 ──────────────────────────
            buildFood("김치찌개",
                "잘 익은 김치와 돼지고기를 넣어 칼칼하게 끓인 한국의 대표 찌개입니다. 밥 한 공기와 함께 먹으면 든든한 한 끼가 완성됩니다.",
                KOREAN, SPICY, RICE, HOT,
                List.of(ing("잘 익은 김치", "200g"), ing("돼지고기 앞다리살", "150g"), ing("두부", "1/2모"), ing("대파", "1대"), ing("고춧가루", "1큰술"), ing("국간장", "1큰술"), ing("물", "500ml")),
                List.of(step(1, "돼지고기를 한입 크기로 썰고 김치도 먹기 좋게 자른다."), step(2, "냄비에 기름을 두르고 돼지고기를 볶다가 김치를 넣어 함께 볶는다."), step(3, "물을 붓고 끓어오르면 고춧가루, 국간장으로 간을 맞춘다."), step(4, "두부를 넣고 10분 더 끓인 뒤 대파를 올려 마무리한다."))),

            buildFood("제육볶음",
                "고추장 양념에 잰 돼지고기를 채소와 함께 볶은 매콤한 한식 반찬입니다. 쌈 채소와 함께 먹으면 더욱 맛있습니다.",
                KOREAN, SPICY, RICE, HOT,
                List.of(ing("돼지고기 앞다리살", "300g"), ing("고추장", "2큰술"), ing("고춧가루", "1큰술"), ing("간장", "1큰술"), ing("설탕", "1작은술"), ing("양파", "1/2개"), ing("대파", "1대"), ing("참기름", "약간")),
                List.of(step(1, "돼지고기를 얇게 썰어 고추장, 고춧가루, 간장, 설탕으로 양념해 30분 재운다."), step(2, "양파와 대파를 먹기 좋게 썬다."), step(3, "팬을 달궈 양념된 돼지고기를 볶는다."), step(4, "고기가 익으면 채소를 넣어 함께 볶고 참기름을 뿌린다."))),

            buildFood("부대찌개",
                "햄, 소시지, 김치, 라면 사리 등을 넣어 끓인 얼큰하고 푸짐한 찌개입니다. 다양한 재료가 어우러져 깊은 맛을 냅니다.",
                KOREAN, SPICY, RICE, HOT,
                List.of(ing("햄", "100g"), ing("소시지", "100g"), ing("김치", "150g"), ing("두부", "1/2모"), ing("라면 사리", "1개"), ing("고추장", "1큰술"), ing("대파", "1대"), ing("육수", "600ml")),
                List.of(step(1, "햄과 소시지를 먹기 좋은 크기로 썬다."), step(2, "냄비에 육수를 붓고 김치, 햄, 소시지를 넣어 끓인다."), step(3, "고추장으로 간을 맞추고 두부를 넣는다."), step(4, "라면 사리와 대파를 넣어 한 번 더 끓인다."))),

            // ── 한식 / 매운 / 면 / 뜨거운 ──────────────────────────
            buildFood("짬뽕",
                "해물과 채소를 넣어 매콤하게 끓인 중화풍 국물 면 요리입니다. 진한 국물과 탱탱한 면의 조화가 일품입니다.",
                KOREAN, SPICY, NOODLE, HOT,
                List.of(ing("중화면", "1인분"), ing("오징어", "1/2마리"), ing("새우", "5마리"), ing("홍합", "5개"), ing("양배추", "100g"), ing("고춧가루", "2큰술"), ing("간장", "1큰술"), ing("육수", "500ml")),
                List.of(step(1, "해물을 손질하고 채소를 먹기 좋게 썬다."), step(2, "팬에 기름을 두르고 고춧가루를 볶아 향을 낸다."), step(3, "해물과 채소를 넣어 볶은 뒤 육수를 붓고 끓인다."), step(4, "면을 삶아 그릇에 담고 국물을 붓는다."))),

            buildFood("라볶이",
                "라면과 떡볶이를 합친 매콤달콤한 분식입니다. 쫄깃한 떡과 면, 달콤한 소스가 잘 어우러집니다.",
                KOREAN, SPICY, NOODLE, HOT,
                List.of(ing("라면 사리", "1개"), ing("떡볶이 떡", "150g"), ing("고추장", "2큰술"), ing("고춧가루", "1큰술"), ing("설탕", "1큰술"), ing("어묵", "100g"), ing("대파", "1대"), ing("물", "400ml")),
                List.of(step(1, "냄비에 물을 붓고 고추장, 고춧가루, 설탕으로 양념장을 만든다."), step(2, "끓어오르면 떡과 어묵을 넣는다."), step(3, "떡이 어느 정도 익으면 라면 사리를 넣는다."), step(4, "대파를 올리고 걸쭉해질 때까지 끓인다."))),

            buildFood("열라면",
                "매운 국물과 쫄깃한 면이 특징인 한국식 매운 라면입니다. 계란과 파를 곁들이면 더욱 맛있습니다.",
                KOREAN, SPICY, NOODLE, HOT,
                List.of(ing("라면", "1봉지"), ing("계란", "1개"), ing("대파", "약간"), ing("물", "550ml")),
                List.of(step(1, "물 550ml를 냄비에 붓고 끓인다."), step(2, "끓어오르면 면과 분말스프, 후레이크를 넣는다."), step(3, "계란을 넣고 2분간 더 끓인다."), step(4, "그릇에 담고 대파를 올린다."))),

            // ── 한식 / 안매운 / 밥 / 뜨거운 ──────────────────────────
            buildFood("된장찌개",
                "구수한 된장에 두부, 애호박, 버섯 등 제철 채소를 넣어 끓인 한국의 대표 가정식 찌개입니다.",
                KOREAN, NOT_SPICY, RICE, HOT,
                List.of(ing("된장", "2큰술"), ing("두부", "1/2모"), ing("애호박", "1/4개"), ing("표고버섯", "2개"), ing("양파", "1/4개"), ing("멸치 육수", "500ml"), ing("다진 마늘", "1작은술")),
                List.of(step(1, "멸치 육수를 준비하고 된장을 풀어 끓인다."), step(2, "두부, 애호박, 버섯, 양파를 먹기 좋게 썬다."), step(3, "육수가 끓으면 채소를 넣고 중간 불로 끓인다."), step(4, "다진 마늘을 넣고 5분 더 끓여 마무리한다."))),

            buildFood("삼겹살 덮밥",
                "노릇하게 구운 삼겹살을 밥 위에 얹고 달콤짭조름한 소스를 뿌린 간단하고 맛있는 덮밥입니다.",
                KOREAN, NOT_SPICY, RICE, HOT,
                List.of(ing("삼겹살", "200g"), ing("밥", "1공기"), ing("간장", "2큰술"), ing("설탕", "1큰술"), ing("참기름", "약간"), ing("대파", "약간"), ing("깨", "약간")),
                List.of(step(1, "삼겹살을 먹기 좋게 썰어 팬에서 굽는다."), step(2, "간장, 설탕, 참기름을 섞어 소스를 만든다."), step(3, "그릇에 밥을 담고 구운 삼겹살을 올린다."), step(4, "소스를 뿌리고 대파, 깨를 올려 마무리한다."))),

            buildFood("계란찜",
                "부드럽고 고소한 한국식 계란 찜입니다. 뚝배기에 담아 보글보글 끓여 먹으면 더욱 맛있습니다.",
                KOREAN, NOT_SPICY, RICE, HOT,
                List.of(ing("계란", "3개"), ing("멸치 육수", "200ml"), ing("소금", "약간"), ing("대파", "약간"), ing("참기름", "약간")),
                List.of(step(1, "계란을 풀고 멸치 육수를 넣어 잘 섞는다."), step(2, "소금으로 간을 맞춘다."), step(3, "뚝배기에 계란물을 붓고 약한 불로 익힌다."), step(4, "표면이 익으면 대파와 참기름을 올린다."))),

            // ── 한식 / 안매운 / 면 / 뜨거운 ──────────────────────────
            buildFood("잔치국수",
                "멸치 육수에 소면을 넣고 계란 지단, 오이, 당근으로 고명을 얹은 깔끔하고 담백한 국수입니다.",
                KOREAN, NOT_SPICY, NOODLE, HOT,
                List.of(ing("소면", "100g"), ing("멸치 육수", "500ml"), ing("계란", "1개"), ing("오이", "1/4개"), ing("당근", "약간"), ing("간장", "1큰술"), ing("소금", "약간")),
                List.of(step(1, "멸치 육수를 끓여 간장, 소금으로 간을 맞춘다."), step(2, "계란을 지단으로 부쳐 채 썰고 오이, 당근도 채 썬다."), step(3, "소면을 삶아 찬물에 헹궈 그릇에 담는다."), step(4, "육수를 붓고 고명을 올린다."))),

            buildFood("우동",
                "굵고 쫄깃한 우동 면에 맑은 가다랑어 국물을 부어 먹는 담백하고 깊은 맛의 면 요리입니다.",
                KOREAN, NOT_SPICY, NOODLE, HOT,
                List.of(ing("우동 면", "1인분"), ing("가다랑어포", "10g"), ing("다시마", "1장"), ing("간장", "2큰술"), ing("미림", "1큰술"), ing("대파", "약간"), ing("어묵", "2장")),
                List.of(step(1, "다시마와 가다랑어포로 육수를 우린다."), step(2, "육수에 간장, 미림으로 간을 맞춘다."), step(3, "우동 면을 삶아 그릇에 담는다."), step(4, "육수를 붓고 어묵, 대파를 올린다."))),

            // ── 한식 / 안매운 / 밥 / 차가운 ──────────────────────────
            buildFood("비빔밥",
                "나물, 고기, 계란 등 다양한 재료를 밥 위에 얹어 고추장과 함께 비벼 먹는 한국의 대표 음식입니다.",
                KOREAN, NOT_SPICY, RICE, COLD,
                List.of(ing("밥", "1공기"), ing("시금치 나물", "50g"), ing("콩나물", "50g"), ing("도라지 나물", "50g"), ing("소고기", "80g"), ing("계란", "1개"), ing("고추장", "1큰술"), ing("참기름", "약간")),
                List.of(step(1, "각 나물을 데쳐 무쳐 준비하고, 소고기는 간장 양념해 볶는다."), step(2, "계란을 반숙으로 프라이한다."), step(3, "그릇에 밥을 담고 나물, 소고기를 색깔별로 올린다."), step(4, "계란 프라이를 올리고 고추장과 참기름을 넣어 비빈다."))),

            buildFood("초밥",
                "신선한 생선을 얇게 썰어 초밥 위에 올린 일본식 요리로, 담백하고 깔끔한 맛이 특징입니다.",
                KOREAN, NOT_SPICY, RICE, COLD,
                List.of(ing("밥", "300g"), ing("식초", "3큰술"), ing("설탕", "2큰술"), ing("소금", "1작은술"), ing("연어", "100g"), ing("참치", "100g"), ing("광어", "100g"), ing("간장", "약간")),
                List.of(step(1, "따뜻한 밥에 식초, 설탕, 소금을 넣어 배합초를 만든다."), step(2, "생선을 적당한 크기로 썬다."), step(3, "밥을 한 입 크기로 뭉쳐 초밥 모양을 만든다."), step(4, "생선을 올리고 간장과 함께 낸다."))),

            // ── 한식 / 매운 / 떡 / 뜨거운 ──────────────────────────
            buildFood("떡볶이",
                "쫄깃한 떡을 매콤달콤한 고추장 소스에 볶은 대한민국 대표 분식입니다.老若男女 누구나 좋아하는 국민 간식입니다.",
                KOREAN, SPICY, TTEOK, HOT,
                List.of(ing("떡볶이 떡", "300g"), ing("고추장", "3큰술"), ing("고춧가루", "1큰술"), ing("설탕", "2큰술"), ing("어묵", "100g"), ing("대파", "1대"), ing("물", "300ml")),
                List.of(step(1, "냄비에 물을 붓고 고추장, 고춧가루, 설탕을 넣어 끓인다."), step(2, "소스가 끓으면 떡과 어묵을 넣는다."), step(3, "중간 불에서 소스가 떡에 배도록 저어가며 끓인다."), step(4, "대파를 넣고 소스가 걸쭉해지면 완성."))),

            buildFood("궁중 떡볶이",
                "간장 기반 달콤짭조름한 양념으로 볶은 조선시대 궁중 음식에서 유래한 떡볶이입니다.",
                KOREAN, SPICY, TTEOK, HOT,
                List.of(ing("떡볶이 떡", "250g"), ing("소고기", "100g"), ing("간장", "2큰술"), ing("설탕", "1큰술"), ing("참기름", "약간"), ing("파", "1대"), ing("깨", "약간"), ing("고추", "2개")),
                List.of(step(1, "소고기를 간장, 설탕으로 양념해 볶는다."), step(2, "떡을 넣고 함께 볶는다."), step(3, "고추와 파를 넣어 향을 더한다."), step(4, "참기름과 깨를 뿌려 마무리한다."))),

            // ── 한식 / 안매운 / 떡 / 차가운 ──────────────────────────
            buildFood("떡케이크",
                "찹쌀떡으로 만든 한국식 케이크입니다. 부드럽고 달콤하며 건강한 재료로 만들어집니다.",
                KOREAN, NOT_SPICY, TTEOK, COLD,
                List.of(ing("찹쌀가루", "200g"), ing("설탕", "50g"), ing("소금", "약간"), ing("팥소", "100g"), ing("식용유", "약간")),
                List.of(step(1, "찹쌀가루에 설탕, 소금, 물을 넣어 반죽한다."), step(2, "찜기에 넣어 20분간 찐다."), step(3, "식힌 뒤 팥소를 넣어 원하는 모양으로 만든다."), step(4, "냉장 보관 후 차갑게 먹는다."))),

            buildFood("인절미",
                "찹쌀을 쪄서 쫄깃하게 만든 뒤 콩고물을 묻혀 먹는 한국의 전통 떡입니다.",
                KOREAN, NOT_SPICY, TTEOK, COLD,
                List.of(ing("찹쌀", "300g"), ing("콩고물", "100g"), ing("소금", "약간"), ing("설탕", "약간")),
                List.of(step(1, "찹쌀을 불려 찜기에서 쪄낸다."), step(2, "뜨거울 때 절구에 넣어 끈기가 생기도록 찧는다."), step(3, "적당한 크기로 자른다."), step(4, "콩고물을 묻혀 완성하고 냉장 보관한다."))),

            // ── 중식 / 매운 / 면 / 뜨거운 ──────────────────────────
            buildFood("마라탕",
                "얼얼하게 매운 마라 향신료와 각종 채소, 고기를 넣어 끓인 중국 쓰촨 스타일 전골입니다.",
                CHINESE, SPICY, NOODLE, HOT,
                List.of(ing("마라 소스", "3큰술"), ing("당면", "100g"), ing("두부", "100g"), ing("버섯", "100g"), ing("청경채", "2포기"), ing("소고기", "100g"), ing("육수", "500ml")),
                List.of(step(1, "육수에 마라 소스를 풀어 끓인다."), step(2, "소고기, 버섯을 넣어 먼저 익힌다."), step(3, "두부, 청경채, 당면을 넣고 끓인다."), step(4, "기호에 맞게 재료를 더 넣어 먹는다."))),

            buildFood("짜장면",
                "춘장을 볶아 만든 달콤하고 고소한 소스를 면 위에 얹어 먹는 한국식 중화요리입니다.",
                CHINESE, NOT_SPICY, NOODLE, HOT,
                List.of(ing("중화면", "1인분"), ing("춘장", "3큰술"), ing("돼지고기", "100g"), ing("양파", "1개"), ing("감자", "1개"), ing("호박", "1/4개"), ing("물", "200ml"), ing("전분물", "2큰술")),
                List.of(step(1, "돼지고기와 채소를 깍둑썰기 한다."), step(2, "팬에 기름을 두르고 춘장을 볶다가 고기와 채소를 넣는다."), step(3, "물을 넣고 끓이다가 전분물로 농도를 맞춘다."), step(4, "삶은 면에 소스를 얹어 낸다."))),

            // ── 중식 / 안매운 / 밥 / 뜨거운 ──────────────────────────
            buildFood("볶음밥",
                "계란과 채소, 고기를 넣어 강한 불에 빠르게 볶은 중국식 볶음밥입니다. 고소하고 담백한 맛이 특징입니다.",
                CHINESE, NOT_SPICY, RICE, HOT,
                List.of(ing("밥", "1공기"), ing("계란", "2개"), ing("햄", "50g"), ing("당근", "30g"), ing("대파", "약간"), ing("간장", "1큰술"), ing("소금", "약간"), ing("참기름", "약간")),
                List.of(step(1, "햄과 당근을 잘게 썬다."), step(2, "팬에 기름을 두르고 계란을 스크램블로 익힌다."), step(3, "햄, 당근을 넣고 볶다가 밥을 넣어 강한 불로 볶는다."), step(4, "간장, 소금으로 간하고 참기름을 뿌린다."))),

            // ── 중식 / 안매운 / 빵 / 뜨거운 ──────────────────────────
            buildFood("꽃빵",
                "찐빵 반죽을 꽃 모양으로 만들어 쪄낸 중국식 빵입니다. 촉촉하고 부드러운 식감이 특징입니다.",
                CHINESE, NOT_SPICY, BREAD, HOT,
                List.of(ing("밀가루", "300g"), ing("이스트", "5g"), ing("설탕", "20g"), ing("소금", "3g"), ing("물", "160ml"), ing("식용유", "약간")),
                List.of(step(1, "밀가루, 이스트, 설탕, 소금, 물을 섞어 반죽한다."), step(2, "반죽을 따뜻한 곳에서 1시간 발효한다."), step(3, "꽃 모양으로 성형한 뒤 15분 더 휴지한다."), step(4, "찜기에서 15분간 쪄낸다."))),

            // ── 일식 / 안매운 / 밥 / 뜨거운 ──────────────────────────
            buildFood("규동",
                "달콤짭조름하게 조린 소고기와 양파를 밥 위에 얹은 일본식 소고기 덮밥입니다.",
                JAPANESE, NOT_SPICY, RICE, HOT,
                List.of(ing("소고기 불고기감", "200g"), ing("양파", "1개"), ing("밥", "1공기"), ing("간장", "3큰술"), ing("미림", "2큰술"), ing("설탕", "1큰술"), ing("계란 노른자", "1개")),
                List.of(step(1, "양파를 얇게 채 썰어 팬에 볶는다."), step(2, "소고기를 넣고 간장, 미림, 설탕으로 조린다."), step(3, "그릇에 밥을 담고 소고기 양파 조림을 얹는다."), step(4, "계란 노른자를 올려 마무리한다."))),

            buildFood("오야코동",
                "닭고기와 계란을 달콤한 국물에 익혀 밥 위에 올린 일본식 닭고기 계란 덮밥입니다.",
                JAPANESE, NOT_SPICY, RICE, HOT,
                List.of(ing("닭가슴살", "150g"), ing("계란", "2개"), ing("양파", "1/2개"), ing("밥", "1공기"), ing("간장", "2큰술"), ing("미림", "2큰술"), ing("다시 육수", "100ml")),
                List.of(step(1, "닭고기와 양파를 먹기 좋게 썬다."), step(2, "팬에 다시 육수, 간장, 미림을 끓이다가 닭고기와 양파를 넣는다."), step(3, "닭이 익으면 풀어 놓은 계란을 둘러 반숙으로 익힌다."), step(4, "밥 위에 얹어 낸다."))),

            // ── 일식 / 안매운 / 면 / 뜨거운 ──────────────────────────
            buildFood("라멘",
                "풍부한 돼지뼈 육수에 쫄깃한 면을 넣고 차슈, 계란, 나루토 등을 올린 일본식 라면입니다.",
                JAPANESE, NOT_SPICY, NOODLE, HOT,
                List.of(ing("라멘 면", "1인분"), ing("돼지뼈 육수", "500ml"), ing("차슈", "3장"), ing("반숙 계란", "1개"), ing("대파", "약간"), ing("죽순", "30g"), ing("간장", "1큰술")),
                List.of(step(1, "돼지뼈를 오랫동안 끓여 진한 육수를 만든다."), step(2, "육수에 간장으로 간을 맞춘다."), step(3, "면을 삶아 그릇에 담는다."), step(4, "육수를 붓고 차슈, 반숙 계란, 대파를 올린다."))),

            // ── 일식 / 안매운 / 면 / 차가운 ──────────────────────────
            buildFood("자루소바",
                "메밀면을 삶아 차갑게 식힌 뒤 쯔유에 찍어 먹는 일본 전통 냉소바입니다.",
                JAPANESE, NOT_SPICY, NOODLE, COLD,
                List.of(ing("소바 면", "100g"), ing("쯔유", "100ml"), ing("와사비", "약간"), ing("대파", "약간"), ing("김", "약간")),
                List.of(step(1, "소바 면을 끓는 물에 3분간 삶는다."), step(2, "삶은 면을 찬물에 헹궈 얼음물에 식힌다."), step(3, "쯔유를 희석해 찍어먹는 소스를 만든다."), step(4, "면을 소쿠리에 담고 와사비, 대파를 곁들인다."))),

            buildFood("냉우동",
                "쫄깃한 우동 면을 차갑게 식혀 달콤한 간장 소스에 먹는 여름 별미 면 요리입니다.",
                JAPANESE, NOT_SPICY, NOODLE, COLD,
                List.of(ing("우동 면", "1인분"), ing("간장", "2큰술"), ing("미림", "1큰술"), ing("가다랑어 육수", "100ml"), ing("대파", "약간"), ing("생강", "약간")),
                List.of(step(1, "우동 면을 끓는 물에 삶는다."), step(2, "삶은 면을 찬물에 헹궈 차갑게 식힌다."), step(3, "간장, 미림, 가다랑어 육수를 섞어 소스를 만든다."), step(4, "면에 소스를 붓고 대파, 생강을 올린다."))),

            // ── 양식 / 안매운 / 빵 / 뜨거운 ──────────────────────────
            buildFood("에그 베네딕트",
                "잉글리시 머핀 위에 햄과 수란을 올리고 풍성한 홀란다이즈 소스를 얹은 고급스러운 브런치 요리입니다.",
                WESTERN, NOT_SPICY, BREAD, HOT,
                List.of(ing("잉글리시 머핀", "1개"), ing("계란", "2개"), ing("햄", "2장"), ing("버터", "50g"), ing("계란 노른자", "2개"), ing("레몬즙", "1큰술"), ing("소금", "약간")),
                List.of(step(1, "노른자, 버터, 레몬즙으로 홀란다이즈 소스를 만든다."), step(2, "끓는 물에 식초를 넣고 계란을 수란으로 익힌다."), step(3, "머핀을 토스트하고 햄을 올린다."), step(4, "수란을 올리고 홀란다이즈 소스를 뿌린다."))),

            buildFood("크로크 무슈",
                "식빵 사이에 햄과 치즈를 넣고 베샤멜 소스를 발라 오븐에 구운 프랑스식 핫 샌드위치입니다.",
                WESTERN, NOT_SPICY, BREAD, HOT,
                List.of(ing("식빵", "2장"), ing("햄", "2장"), ing("그뤼에르 치즈", "50g"), ing("버터", "20g"), ing("밀가루", "1큰술"), ing("우유", "100ml"), ing("소금", "약간")),
                List.of(step(1, "버터, 밀가루, 우유로 베샤멜 소스를 만든다."), step(2, "식빵에 버터를 바르고 햄과 치즈를 올려 샌드위치를 만든다."), step(3, "겉면에 베샤멜 소스를 바르고 치즈를 뿌린다."), step(4, "오븐 180도에서 10분간 굽는다."))),

            // ── 양식 / 안매운 / 면 / 뜨거운 ──────────────────────────
            buildFood("카르보나라",
                "계란 노른자와 파르메산 치즈로 만든 크리미한 소스에 베이컨을 넣은 이탈리아 정통 파스타입니다.",
                WESTERN, NOT_SPICY, NOODLE, HOT,
                List.of(ing("스파게티", "100g"), ing("베이컨", "100g"), ing("계란 노른자", "2개"), ing("파르메산 치즈", "50g"), ing("후추", "약간"), ing("소금", "약간")),
                List.of(step(1, "스파게티를 소금물에 알덴테로 삶는다."), step(2, "베이컨을 팬에 볶아 기름을 낸다."), step(3, "불을 끄고 계란 노른자, 치즈, 면수를 넣어 소스를 만든다."), step(4, "면을 넣어 버무리고 후추를 뿌린다."))),

            buildFood("봉골레 파스타",
                "신선한 조개와 마늘, 화이트 와인으로 만든 깔끔하고 해산물 향 가득한 이탈리아 파스타입니다.",
                WESTERN, NOT_SPICY, NOODLE, HOT,
                List.of(ing("스파게티", "100g"), ing("바지락", "200g"), ing("마늘", "4쪽"), ing("화이트 와인", "50ml"), ing("올리브 오일", "3큰술"), ing("파슬리", "약간"), ing("소금", "약간")),
                List.of(step(1, "바지락을 해감하고 마늘은 편 썬다."), step(2, "팬에 올리브 오일을 두르고 마늘을 볶는다."), step(3, "바지락과 화이트 와인을 넣고 조개가 입을 열 때까지 익힌다."), step(4, "삶은 파스타를 넣어 버무리고 파슬리를 뿌린다."))),

            // ── 양식 / 매운 / 면 / 뜨거운 ──────────────────────────
            buildFood("알리오 올리오 페퍼론치노",
                "올리브 오일, 마늘, 페퍼론치노로 만든 매콤하고 심플한 이탈리아 파스타입니다.",
                WESTERN, SPICY, NOODLE, HOT,
                List.of(ing("스파게티", "100g"), ing("마늘", "5쪽"), ing("페퍼론치노", "3개"), ing("올리브 오일", "4큰술"), ing("파슬리", "약간"), ing("소금", "약간")),
                List.of(step(1, "스파게티를 소금물에 알덴테로 삶는다."), step(2, "팬에 올리브 오일을 두르고 마늘과 페퍼론치노를 볶는다."), step(3, "면수를 넣어 소스를 만든다."), step(4, "파스타를 넣어 버무리고 파슬리를 뿌린다."))),

            buildFood("아라비아타 파스타",
                "토마토 소스에 페퍼론치노를 넣어 매콤하게 만든 이탈리아 파스타입니다.",
                WESTERN, SPICY, NOODLE, HOT,
                List.of(ing("펜네", "100g"), ing("토마토 소스", "200ml"), ing("페퍼론치노", "3개"), ing("마늘", "3쪽"), ing("올리브 오일", "2큰술"), ing("파르메산 치즈", "약간"), ing("바질", "약간")),
                List.of(step(1, "펜네를 소금물에 삶는다."), step(2, "팬에 올리브 오일로 마늘과 페퍼론치노를 볶는다."), step(3, "토마토 소스를 넣고 끓인다."), step(4, "펜네를 넣어 버무리고 치즈와 바질을 올린다.")))
        );

        foodRepository.saveAll(foods);
        log.info("[Seed] 완료! 총 {}개 음식 저장", foods.size());
    }

    private Food buildFood(String name, String description,
                           Food.CuisineType cuisineType, Food.SpicyLevel spicyLevel,
                           Food.FoodType foodType, Food.TempType tempType,
                           List<Ingredient> ingredients, List<RecipeStep> recipeSteps) {
        Food food = Food.builder()
                .name(name)
                .description(description)
                .cuisineType(cuisineType)
                .spicyLevel(spicyLevel)
                .foodType(foodType)
                .tempType(tempType)
                .ingredients(new java.util.ArrayList<>())
                .recipeSteps(new java.util.ArrayList<>())
                .build();

        ingredients.forEach(i -> {
            Ingredient ing = Ingredient.builder()
                    .food(food).name(i.getName()).amount(i.getAmount()).build();
            food.getIngredients().add(ing);
        });

        recipeSteps.forEach(r -> {
            RecipeStep rs = RecipeStep.builder()
                    .food(food).stepOrder(r.getStepOrder()).description(r.getDescription()).build();
            food.getRecipeSteps().add(rs);
        });

        return food;
    }

    private Ingredient ing(String name, String amount) {
        return Ingredient.builder().name(name).amount(amount).build();
    }

    private RecipeStep step(int order, String description) {
        return RecipeStep.builder().stepOrder(order).description(description).build();
    }
}
