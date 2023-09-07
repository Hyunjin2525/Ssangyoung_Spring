package com.sist.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

@Service
public class test {

    public void scrapeWebsite() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe"); 
        // Chrome WebDriver 설정
        WebDriver driver = new ChromeDriver();
        try {
            // 웹 페이지 접속
            driver.get("https://gocamping.or.kr/bsite/camp/info/list.do?pageUnit=10&searchKrwd=&listOrdrTrget=c_rdcnt&pageIndex=1");

            // 최대 10초간 특정 요소를 기다림
            WebDriverWait wait = new WebDriverWait(driver, 10);

            // 원하는 요소가 로드될 때까지 기다리기 (a 태그까지 포함한 선택자 사용)
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.camp_search_list .c_list > a")));
            element.click();
            // a 태그의 href 속성 가져오기
            String data = element.getAttribute("href");

            // 요소의 텍스트와 href 속성 출력
            System.out.println("Element Text: " + element.getText());
            System.out.println("href Attribute: " + data);
        } finally {
            // 웹 드라이버 종료
            driver.quit();
        }
    }

    public static void main(String[] args) {
        test t = new test();
        t.scrapeWebsite(); // 웹 크롤링 메서드 호출
        System.out.println("완료");
    }
}
