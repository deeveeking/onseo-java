package tests;

import static org.assertj.core.api.Assertions.assertThat;

import base.TestBase;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import pages.LeftNavPage;
import pages.MainPage;
import pages.MatchPage;
import pages.SettingsPage;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class MyTest extends TestBase {

    @Test
    public void changeTimeZoneTimeTest() {
        String newTimeZone = "UTC +14:00";
        MainPage mainPage = new MainPage(driver).get();

        MatchPage matchPage = mainPage.openFirstUpcomingMatch();

        LocalTime matchTimeBefore = LocalTime.parse(matchPage.getMatchScoreOrTime().getText());

        matchPage.getOpenLeftMenuButton().click();

        LeftNavPage leftNavPage = new LeftNavPage(driver).get();
        leftNavPage.getSettingsButton().click();

        SettingsPage settingsPage = new SettingsPage(driver).get();
        settingsPage.changeTimeZone(newTimeZone);

        LocalTime matchTimeAfter = LocalTime.parse(matchPage.getMatchScoreOrTime().getText());

        long hourMatchTimeDifference = getHourDifference(matchTimeBefore, matchTimeAfter);
        LocalTime newExpectedMatchTime = matchTimeBefore.plusHours(hourMatchTimeDifference);

        assertThat(matchTimeAfter)
                .as("New match time should be correct")
                .isEqualTo(newExpectedMatchTime.plusHours(1));
    }

    @Test
    public void changeTimeZoneDateTest() {
        String newTimeZone = "UTC +14:00";
        MainPage mainPage = new MainPage(driver).get();

        MatchPage matchPage = mainPage.openFirstUpcomingMatch();

        LocalTime matchTimeBefore = LocalTime.parse(matchPage.getMatchScoreOrTime().getText());
        String matchDateBefore = matchPage.getMatchDate().getText();

        matchPage.getOpenLeftMenuButton().click();

        LeftNavPage leftNavPage = new LeftNavPage(driver).get();
        leftNavPage.getSettingsButton().click();

        SettingsPage settingsPage = new SettingsPage(driver).get();
        settingsPage.changeTimeZone(newTimeZone);

        String matchDateAfter = matchPage.getMatchDate().getText();
        String expectedNewDate = getNewDate(matchDateBefore, matchTimeBefore);

        assertThat(matchDateAfter)
                .as("Match time should be correct")
                .isEqualTo(expectedNewDate);
    }

    private long getHourDifference(LocalTime firstTime, LocalTime secondTime) {
        return ChronoUnit.HOURS.between(firstTime, secondTime);
    }

    private String getNewDate(String firstDate, LocalTime matchTimeBefore) {
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd MMM").toFormatter(Locale.ENGLISH);
        String newDay;
        String month = LocalDate.now().getMonth().toString().substring(0, 3).toLowerCase();
        int currentDay = LocalDate.now().getDayOfMonth();

        if (firstDate.equals("Today") && matchTimeBefore.isAfter(LocalTime.parse("13:00"))) {
            currentDay += 1;
        } else if (!(firstDate.equals("Today")) && matchTimeBefore.isAfter(LocalTime.parse("13:00"))) {
            currentDay = MonthDay.from(df.parse(firstDate)).getDayOfMonth() + 1;
        }

        if (currentDay >= 10) {
            newDay = String.format("%s %s",currentDay , StringUtils.capitalize(month));
        } else {
            newDay = String.format("0%s %s",currentDay , StringUtils.capitalize(month));
        }
        // need to add check if it's today or it's tomorrow and day not change

        return newDay;
    }
}
