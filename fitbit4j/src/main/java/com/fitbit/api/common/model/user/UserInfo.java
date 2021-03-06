package com.fitbit.api.common.model.user;

import com.fitbit.api.common.service.FitbitApiService;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.json.JSONException;
import org.json.JSONObject;

public class UserInfo {

    private final String encodedId;
    private final String displayName;
    private final Gender gender;
    private final LocalDate dateOfBirth;
    private final double height;
    private final double strideLengthWalking;
    private final double strideLengthRunning;
    private final String fullName;
    private final String nickname;
    private final String country;
    private final String state;
    private final String city;
    private final String aboutMe;
    private final DateTimeZone timezone;
    /**
     * Millisecond offset to add to UTC to get timezone
     */
    private final int offsetFromUTCMillis;

    public UserInfo(String encodedId, String displayName, Gender gender, LocalDate dateOfBirth,
                    double height, double strideLengthWalking, double strideLengthRunning, String fullName,
                    String nickname, String country, String state, String city, String aboutMe, DateTimeZone timezone, int offsetFromUTCMillis) {
        this.encodedId = encodedId;
        this.displayName = displayName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.strideLengthWalking = strideLengthWalking;
        this.strideLengthRunning = strideLengthRunning;
        this.fullName = fullName;
        this.nickname = nickname;
        this.country = country;
        this.state = state;
        this.city = city;
        this.aboutMe = aboutMe;
        this.timezone = timezone;
        this.offsetFromUTCMillis = offsetFromUTCMillis;
    }

    public UserInfo(JSONObject json) throws JSONException {
        JSONObject userJson = json.getJSONObject("user");
        encodedId = userJson.getString("encodedId");
        displayName = userJson.getString("displayName");
        gender = Gender.valueOf(userJson.getString("gender"));
        dateOfBirth = FitbitApiService.getValidLocalDateOrNull(userJson.optString("dateOfBirth"));
        height = userJson.optDouble("height");
        strideLengthWalking = userJson.optDouble("strideLengthWalking");
        strideLengthRunning = userJson.optDouble("strideLengthRunning");
        fullName = userJson.optString("fullName");
        nickname = userJson.optString("nickname");
        country = userJson.optString("country");
        state = userJson.optString("state");
        city = userJson.optString("city");
        aboutMe = userJson.optString("aboutMe");
        timezone = DateTimeZone.forID(userJson.getString("timezone"));
        offsetFromUTCMillis = userJson.optInt("offsetFromUTCMillis");
    }

    public String getEncodedId() {
        return encodedId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return null == dateOfBirth ? "" : FitbitApiService.LOCAL_DATE_FORMATTER.print(dateOfBirth);
    }

    public LocalDate dateOfBirth() {
        return dateOfBirth;
    }

    public double getHeight() {
        return height;
    }

    public double getStrideLengthWalking() {
        return strideLengthWalking;
    }

    public double getStrideLengthRunning() {
        return strideLengthRunning;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public String getTimezone() {
        return timezone.toString();
    }

    public DateTimeZone timezone() {
        return timezone;
    }

    public int getOffsetFromUTCMillis() {
        return offsetFromUTCMillis;
    }
}
