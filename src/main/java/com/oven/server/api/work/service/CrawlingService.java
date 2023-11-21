package com.oven.server.api.work.service;

import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.repository.UserRepository;
import com.oven.server.api.work.domain.Genre;
import com.oven.server.api.work.domain.Provider;
import com.oven.server.api.work.domain.Work;
import com.oven.server.api.work.domain.WorkProvider;
import com.oven.server.api.work.repository.ProviderRepository;
import com.oven.server.api.work.repository.WorkProviderRepository;
import com.oven.server.api.work.repository.WorkRepository;
import com.oven.server.common.exception.BaseException;
import com.oven.server.common.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CrawlingService {
    private final UserRepository userRepository;
    private final WorkRepository workRepository;
    private final ProviderRepository providerRepository;
    private final WorkProviderRepository workProviderRepository;

    public void saveWork() {

        String chromeDriverPath = "/Users/siyoung/졸업프로젝트/oven-server/chromedriver";

        //세션 시작
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        //페이지가 로드될 때까지 대기
        //Normal: 로드 이벤트 실행이 반환 될 때 까지 기다린다.
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        WebDriver homeDriver = new ChromeDriver(options);
        //driver1, 상세보기 페이지 때문에 하나 더 만듦
        WebDriver detailDriver = new ChromeDriver(options);

        try {
            //페이지 이동
            homeDriver.navigate().to("https://m.kinolights.com/discover/explore"); //홈페이지
            detailDriver.navigate().to("https://m.kinolights.com/discover/explore");
            log.info("--------homeDriver 이동: " + detailDriver.getCurrentUrl() + "--------");
            log.info("--------detailDriver 이동:  " + detailDriver.getCurrentUrl() + "--------");

            WebElement checkbox = homeDriver.findElement(By.id("checkSubscription"));
            ((JavascriptExecutor) homeDriver).executeScript("arguments[0].click();", checkbox);
            log.info("--------정액제만 보기 checkbox 선택--------");

            WebElement item = homeDriver.findElement(By.cssSelector(".MovieItem.grid"));

            var stTime = new Date().getTime(); //현재시간
            while (new Date().getTime() < stTime + 10000) { //10초 동안 무한스크롤 지속
                Thread.sleep(500); //리소스 초과 방지
                //executeScript: 해당 페이지에 JavaScript 명령을 보내는 거
                ((JavascriptExecutor) homeDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)", item);
            }
            log.info("--------무한 스크롤 완료--------");

            List<WebElement> elements = homeDriver.findElements(By.cssSelector(".MovieItem.grid"));
            log.info("--------" + elements.size() + "개의 작품--------");

            for (WebElement element : elements) {

                homeDriver.manage().timeouts().implicitlyWait(100000, TimeUnit.MILLISECONDS);
                detailDriver.manage().timeouts().implicitlyWait(100000, TimeUnit.MILLISECONDS);

                String url = element.findElement(By.tagName("a")).getAttribute("href");
                detailDriver.navigate().to(url); //url로 이동
                log.info("--------detailDriver 이동: " + url + "--------");

                Thread.sleep(500);

                // title-kr
                String titleKr = detailDriver.findElement(By.className("title-kr")).getText();
                log.info("--------title(kr): " + titleKr + "--------");

                // title-eng, year
                List<WebElement> titleEngAndYear = detailDriver.findElements(By.className("metadata-item"));
                String titleEng = titleEngAndYear.get(0).getText();
                int year = Integer.parseInt(titleEngAndYear.get(1).getText());
                log.info("--------title(eng): " + titleEng + "--------");
                log.info("--------year: " + year + "--------");

//              // movie or tv
//              if (detailDriver.findElements(By.className("tv-label")).isEmpty()) {
//                  System.out.println("isMovie = true");
//              } else {
//                  System.out.println("isMovie = false");
//              }

                // poster 조회
                String poster = element.findElement(By.tagName("img")).getAttribute("data-src");
                log.info("--------poster: " + poster + "--------");


                // 관람연령등급, 장르
                WebElement ul = detailDriver.findElement(By.className("movie-metadata-area"));
                List<WebElement> tags = ul.findElements(By.className("metadata"));

                String rating = "";
                Genre genre = new Genre();

                for (WebElement tag : tags) {
                    switch (tag.getText()) {
                        case "#15세이상관람가":
                        case "#12세이상관람가":
                        case "#전체관람가":
                        case "#청소년관람불가":
                            rating = tag.getText();
                            break;
                        case "#액션":
                            System.out.print("action/");
                            genre.setAction(true);
                            break;
                        case "#SF":
                            System.out.print("SF/");
                            genre.setSF(true);
                            break;
                        case "#판타지":
                            System.out.print("fantasy/");
                            genre.setFantasy(true);
                            break;
                        case "#어드벤처(모험)":
                            System.out.print("adventure/");
                            genre.setAdventure(true);
                            break;
                        case "#범죄":
                            System.out.print("criminal/");
                            genre.setCriminal(true);
                            break;
                        case "#스릴러":
                            System.out.print("thriller/");
                            genre.setThriller(true);
                            break;
                        case "#미스터리":
                            System.out.print("mystery/");
                            genre.setMystery(true);
                            break;
                        case "#코미디":
                            System.out.print("comedy/");
                            genre.setComedy(true);
                            break;
                        case "#멜로/로맨스":
                            System.out.print("romance/");
                            genre.setRomance(true);
                            break;
                        case "#드라마":
                            System.out.print("drama/");
                            genre.setDrama(true);
                            break;
                        case "#애니메이션":
                            System.out.print("animation/");
                            genre.setAnimation(true);
                            break;
                        case "#공포(호러)":
                            System.out.print("horror/");
                            genre.setHorror(true);
                            break;
                        case "#예능":
                            System.out.print("variety/");
                            genre.setVariety(true);
                            break;
                        case "#다큐멘터리":
                            System.out.print("documentary/");
                            genre.setDocumentary(true);
                            break;
                        case "#뮤지컬":
                            System.out.print("musical/");
                            genre.setMusical(true);
                            break;
                        case "#가족":
                            System.out.print("family/");
                            genre.setFamily(true);
                            break;
                        case "#서부극(웨스턴)":
                            System.out.print("western/");
                            genre.setWestern(true);
                            break;
                        case "#전쟁":
                            System.out.print("war/");
                            genre.setWar(true);
                            break;
                        case "#공연":
                            System.out.print("performance/");
                            genre.setPerformance(true);
                            break;
                        case "#성인":
                            System.out.print("adult/");
                            genre.setAdult(true);
                            break;
                        case "#음악":
                            System.out.print("music/");
                            genre.setMusic(true);
                            break;
                    }
                }

                log.info("--------rating: " + rating + "--------");
                log.info("--------genre: " + genre.toString() + "--------");

                // 줄거리
                String summary = detailDriver.findElement(By.className("synopsis")).getText();
                log.info("----summary: " + summary + "--------");

//                String trailer = null;
//                // 트레일러
//                if(detailDriver.findElement(By.className("youtube-trailer")).isDisplayed()) {
//                    trailer = detailDriver.findElement(By.className("youtube-trailer")).getAttribute("src");
//                    log.info("----trailer: " + trailer + "--------");
//                }


                // 감독, 배우
                By moreButtonLocator = By.className("action__more-button");

                List<WebElement> moreButtons = detailDriver.findElements(moreButtonLocator);
                for (WebElement button : moreButtons) {
                    if (button.isDisplayed()) {
                        ((JavascriptExecutor) detailDriver).executeScript("arguments[0].click();", button);
                        log.info("--------더보기 버튼 선택--------");
                    }
                }

//                List<WebElement> people = detailDriver.findElements(By.className("person"));

                String directors = "";
                String actors = "";

                try {
                    List<WebElement> staffList = detailDriver.findElement(By.id("staffList")).findElements(By.className("person"));
                    for (WebElement staff : staffList) {
                        if (staff.findElement(By.className("character")).getText().equals("감독") || staff.findElement(By.className("character")).getText().equals("연출")) {
                            directors = directors + staff.findElement(By.className("name")).getText() + ", ";
                        }
                    }
                } catch (NoSuchElementException e) {
                    directors = "";
                }
                try {
                    List<WebElement> actorList = detailDriver.findElement(By.id("actorList")).findElements(By.className("person"));
                    int i = 0;
                    for (WebElement actor : actorList) {
                        i = i+1;
                        actors = actors + actor.findElement(By.className("name")).getText() + ", ";
                        if (i >= 10) break;
                    }
                } catch (NoSuchElementException e) {
                    actors = "";
                }

//                for (WebElement person : people) {
//                    if (person.findElement(By.xpath("//*[@id=\"staffList\"]/div[1]/div[2]")).getText().equals("감독")) {
//                        directors = directors + person.findElement(By.className("name")).getText() + ", ";
//                    } else {
//                        actors = actors + person.findElement(By.className("name")).getText() + ", ";
//                    }
//                }
                log.info("----directors: " + directors + "--------");
                log.info("----actors: " + actors + "--------");

                Work work = Work.builder()
                        .titleKr(titleKr)
                        .titleEng(titleEng)
                        .year(year)
                        .ageRating(rating)
                        .poster(poster)
                        .summary(summary)
                        .director(directors)
                        .actor(actors)
                        .genre(genre)
                        .build();

                Work savedWork = workRepository.saveAndFlush(work);

                // provider
                List<WebElement> providers = detailDriver.findElements(By.className("price-item-provider"));

                for (WebElement provider : providers) {
                    log.info("----provider: " + provider.findElement(By.tagName("span")).getText() + "--------");
                    switch (provider.findElement(By.tagName("span")).getText()) {
                        case "넷플릭스":
                            saveWorkProvider("NETFLIX", savedWork);
                            break;
                        case "티빙":
                            saveWorkProvider("TVING", savedWork);
                            break;
                        case "웨이브":
                            saveWorkProvider("WAVVE", savedWork);
                            break;
                        case "왓챠":
                            saveWorkProvider("WATCHA", savedWork);
                            break;
                        case "디즈니+":
                            saveWorkProvider("DISNEY_PLUS", savedWork);
                            break;
                        case "쿠팡플레이":
                            saveWorkProvider("COUPANG_PLAY", savedWork);
                            break;
                        case "Apple TV":
                            saveWorkProvider("APPLE_TV", savedWork);
                            break;
                        case "네이버 시리즈온":
                            saveWorkProvider("NAVER_SERIESON", savedWork);
                            break;
                        case "Google Play 무비":
                            saveWorkProvider("GOOGLE_PLAY", savedWork);
                            break;
                        case "U+모바일tv":
                            saveWorkProvider("UPLUS_MOBILE_TV", savedWork);
                            break;
                        case "아마존 프라임 비디오":
                            saveWorkProvider("AMAZON_PRIME_VIDEO", savedWork);
                            break;
                        case "라프텔":
                            saveWorkProvider("LAFTEL", savedWork);
                            break;
                        case "씨네폭스":
                            saveWorkProvider("CINEFOX", savedWork);
                            break;
                    }
                }
            }

        } catch (InterruptedException e) {

            e.printStackTrace();

        } finally {

            // 세션 종료
            homeDriver.quit();
            detailDriver.quit();

        }

    }

    private void saveWorkProvider(String providerName, Work work) {
        Provider findProvider = providerRepository.findByName(providerName).orElseThrow(
                () -> new BaseException(ResponseCode.PROVIDER_NOT_FOUND)
        );

        log.info("--------work: " + work + "--------");
        log.info("--------findProvider: " + findProvider + "--------");

        WorkProvider workProvider = new WorkProvider();
        workProvider.setProvider(findProvider);
        workProvider.setWork(work);
        workProviderRepository.save(workProvider);
    }

    public void saveRatingFile() {
        String filePath = "/Users/hs/Rating.csv";
        File file = null;
        BufferedWriter bw = null;
        String NEWLINE = System.lineSeparator();
        try {
            Random random = new Random();
            List<User> users = userRepository.findAll();
            file = new File(filePath);
            bw = new BufferedWriter(new FileWriter(file));
            bw.write("user_id, work_id, rating");
            bw.write(NEWLINE);
            for (int i = 0; i < users.size(); i++) {
                List<Work> randomWorks = workRepository.findRandoms();
                String userIdStr = String.valueOf(users.get(i).getId());
                for (int j = 0; j < randomWorks.size(); j++) {
                    String wordIdStr = String.valueOf(randomWorks.get(j).getId());
                    String rating = String.valueOf(random.nextInt(4)+2);
                    String totalstr = userIdStr + "," + wordIdStr + "," + rating;
                    bw.write(totalstr);
                    bw.write("\n");
                }
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

