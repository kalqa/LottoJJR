package pl.lotto.domain;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AdjustableClock extends Clock {

    private final ZoneId zone;
    protected Instant instant;

    public AdjustableClock(Instant initialInstant, ZoneId zone) {
        this.instant = initialInstant;
        this.zone = zone;
    }

    public static AdjustableClock ofLocalDateAndLocalTime(LocalDate date, LocalTime time, ZoneId zone) {
        ZonedDateTime zoneDateTime = createZoneDateTime(date, time, zone);
        return new AdjustableClock(zoneDateTime.toInstant(), zone);
    }

    protected static ZonedDateTime createZoneDateTime(LocalDate date, LocalTime time, ZoneId zone) {
        return ZonedDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(),
                time.getHour(), time.getMinute(), time.getSecond(), time.getNano(), zone);
    }

    @Override
    public ZoneId getZone() {
        return zone;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        if (zone.equals(this.zone)) {  // intentional NPE
            return this;
        }
        return new AdjustableClock(instant, zone);
    }

    @Override
    public long millis() {
        return instant().toEpochMilli();
    }

    @Override
    public Instant instant() {
        return instant;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AdjustableClock other
                && instant.equals(other.instant)
                && zone.equals(other.zone);
    }

    @Override
    public int hashCode() {
        return instant.hashCode() ^ zone.hashCode();
    }

    @Override
    public String toString() {
        return "AdjustableClock[" + instant + "," + zone + "]";
    }

    public void advanceInTimeBy(Duration clockOffset) {
        this.instant = instant.plus(clockOffset);
    }

    public void plusDays(int days) {
        Duration offset = Duration.ofDays(days);
        advanceInTimeBy(offset);
    }

    public void plusDaysAndMinutes(int days, int minutes) {
        Duration offset = Duration.ofDays(days);
        advanceInTimeBy(offset);
        Duration ofMinutes = Duration.ofMinutes(minutes);
        advanceInTimeBy(ofMinutes);
    }

    public void setClockToLocalDateTime(LocalDateTime localDateTime) {
        ZonedDateTime zoneDateTime = createZoneDateTime(localDateTime.toLocalDate(), localDateTime.toLocalTime(), zone);
        this.instant = zoneDateTime.toInstant();
    }

    public void setClockToLocalDate(LocalDate localDate) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.now(this));
        setClockToLocalDateTime(localDateTime);
    }

    public void setClockToLocalTime(LocalTime localTime) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(this), localTime);
        setClockToLocalDateTime(localDateTime);
    }

    public void plusMinutes(int minutes) {
        Duration offset = Duration.ofMinutes(minutes);
        advanceInTimeBy(offset);
    }
}

