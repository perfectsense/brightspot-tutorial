package com.company.employee;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.company.address.Address;
import com.company.address.StateProvince;
import com.company.company.Company;
import com.company.company.CompanyDataGenerator;
import com.company.debug.ResourceFileReader;
import com.company.position.Position;
import com.company.position.PositionDataGenerator;
import com.company.team.Team;
import com.company.team.TeamDataGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.psddev.dari.db.Query;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.ClassFinder;
import com.psddev.dari.util.CollectionUtils;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.Settings;
import com.psddev.dari.util.StorageItem;
import com.psddev.dari.util.StorageItemAfterSave;
import com.psddev.dari.util.StorageItemBeforeSave;
import com.psddev.dari.util.StorageItemUploadPart;
import com.psddev.dari.util.StringUtils;
import com.psddev.dari.util.TypeDefinition;
import com.psddev.dari.util.TypeReference;

public class EmployeeDataGenerator {

    private Consumer<Record> recordUpdater;
    private Random random;

    public EmployeeDataGenerator(Consumer<Record> recordUpdater) {
        this(recordUpdater, new Random());
    }

    public EmployeeDataGenerator(Consumer<Record> recordUpdater, Random random) {
        this.recordUpdater = recordUpdater;
        this.random = random;
    }

    public List<Employee> generateEmployees(int desiredAmount) throws IOException {

        List<Employee> saved = new ArrayList<>();

        List<Employee> employees = generateEmployees(recordUpdater, random, desiredAmount);

        for (Employee employee : employees) {

            try {
                if (recordUpdater != null) {
                    recordUpdater.accept(employee);
                }

                employee.save();

                saved.add(employee);

            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

        return saved;
    }

    private static List<Employee> generateEmployees(Consumer<Record> recordUpdater, Random random, int desiredAmount) throws IOException {

        StreetData streetData = StreetData.load();
        CityStateZipData cityStateZipData = CityStateZipData.load();
        AreaCodeData areaCodeData = AreaCodeData.load();
        PersonNameData personNameData = PersonNameData.load();
        SkillsData skillsData = SkillsData.load();
        AvatarData avatarData = AvatarData.load();

        Company company = Query.from(Company.class).first();
        if (company.getName() == null) {
            company = new CompanyDataGenerator(recordUpdater, random).generateCompany();
        }

        String companyName = null;
        if (company != null) {
            companyName = company.getName();
        }
        if (companyName == null) {
            companyName = "Company";
        }

        List<Team> teamsData = Query.from(Team.class).selectAll();
        if (teamsData.isEmpty()) {
            teamsData = new TeamDataGenerator(recordUpdater).generateTeams();
        }

        List<Position> positionsData = Query.from(Position.class).selectAll();
        if (positionsData.isEmpty()) {
            positionsData = new PositionDataGenerator(recordUpdater).generatePositions();
        }

        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < desiredAmount; i++) {

            CityStateZip cityStateZip = cityStateZipData.getRandomCityStateZip(random);
            StateProvince stateProvince = StateProvince.fromAbbreviation(cityStateZip.getStateAbbreviation());
            PersonName personName = personNameData.getUniqueRandomPersonName(random);

            boolean useUrl = true;
            StorageItem avatarFile;

            if (useUrl) {
                String avatarUrl = avatarData.getRandomAvatarUrl(random, personName.getGender());
                avatarFile = StorageItem.Static.createUrl(avatarUrl);
            } else {
                String avatarPath = avatarData.getRandomAvatarResourcePath(random, personName.getGender());
                avatarFile = createImageStorageItemFromResource(avatarPath);
            }

            String firstName = personName.getFirstName();
            String lastName = personName.getLastName();

            StorageItem profilePic = avatarFile;
            String phoneNumber = areaCodeData.getRandomPhoneNumberForState(random, stateProvince != null ? stateProvince.getName() : null);
            String email = personName.getEmailAddress(StringUtils.toNormalized(companyName).replace("-", "") + ".com");
            String slackId = personName.getSlackId(random);

            Address address = new Address();
            address.setStreet(streetData.getRandomStreet(random));
            address.setCity(cityStateZip.getCity());
            address.setStateProvince(stateProvince);
            address.setZipCode(cityStateZip.getZip());

            String shortBio = null;
            List<String> skills = new ArrayList<>(skillsData.getRandomSkills(random, 0, 5));
            Date dateOfBirth = generateRandomBirthDate(random);
            Date dateOfHire = generateRandomStartDate(random, dateOfBirth);
            Date dateOfTermination = null;

            Position position = positionsData.get(random.nextInt(positionsData.size()));

            Set<Team> teams;
            if (random.nextInt(10) <= 8) {
                teams = getRandomSet(random, 0, 6, teamsData);
            } else {
                teams = getRandomSet(random, 1, 5, teamsData);
            }

            // create the employee

            Employee employee = new Employee();

            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setPosition(position);
            employee.setProfilePic(profilePic);
            employee.setPhoneNumber(phoneNumber);
            employee.setEmail(email);
            employee.setSlackId(slackId);
            employee.setAddress(address);
            employee.setShortBio(shortBio);
            employee.setSkills(skills);
            employee.setDateOfBirth(dateOfBirth);
            employee.setDateOfHire(dateOfHire);
            employee.setDateOfTermination(dateOfTermination);
            employee.setTeams(teams);

            employees.add(employee);
        }

        return employees;
    }

    private static Date generateRandomBirthDate(Random random) {
        return generateRandomDateYearsAgo(random, 18, 65);
    }

    private static Date generateRandomStartDate(Random random, Date birthDate) {
        int age;

        if (birthDate != null) {
            age = Period.between(birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();
        } else {
            age = 65;
        }

        return generateRandomDateYearsAgo(random, 0, Integer.min(15, age - 17));
    }

    private static Date generateRandomDateYearsAgo(Random random, int minYears, int maxYears) {

        Instant instant = new Date().toInstant();

        Instant max = instant.minus(Duration.ofDays(365 * minYears));
        Instant min = max.minus(Duration.ofDays(365 * (maxYears - minYears)));

        long maxSeconds = max.getEpochSecond();
        long minSeconds = min.getEpochSecond();

        long randomSeconds = minSeconds + (long) (random.nextDouble() * (maxSeconds - minSeconds));

        return new Date(randomSeconds * 1000);
    }

    private static StorageItem createImageStorageItemFromResource(String resourcePath) throws IOException {

        StorageItem storageItem = StorageItem.Static.create();

        String contentType = null;

        int lastDotAt = resourcePath.lastIndexOf('.');
        if (lastDotAt > 0) {
            contentType = "image/" + resourcePath.substring(lastDotAt + 1);
        }

        storageItem.setContentType(contentType);
        storageItem.setPath(resourcePath);
        storageItem.setData(ResourceFileReader.getFileAsInputStream(resourcePath));

        Map<String, Object> metadata = storageItem.getMetadata();
        if (metadata == null) {
            metadata = new LinkedHashMap<>();
        }
        if (!metadata.containsKey("cms.crops")) {
            metadata.put("cms.crops", new HashMap<>());
        }
        if (!metadata.containsKey("cms.focus")) {
            metadata.put("cms.focus", new HashMap<>());
        }
        if (!metadata.containsKey("cms.edits")) {
            metadata.put("cms.edits", new HashMap<>());
        }

        StorageItemUploadPart part = new StorageItemUploadPart();

        part.setContentType(contentType);
        // Using StringUtils.getFileName as fileItem.getName may include the path
        part.setName(StringUtils.getFileName(resourcePath));
        try {
            part.setFile(new File(ResourceFileReader.getFileAsURL(resourcePath).toURI()));
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
        part.setStorageName(Settings.get(String.class, StorageItem.DEFAULT_STORAGE_SETTING));

        // Add additional beforeSave functionality through StorageItemBeforeSave implementations
        ClassFinder.findConcreteClasses(StorageItemBeforeSave.class)
                .forEach(c -> {
                    try {
                        TypeDefinition.getInstance(c).newInstance().beforeSave(storageItem, part);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        storageItem.save();

        // Add additional afterSave functionality through StorageItemAfterSave implementations
        ClassFinder.findConcreteClasses(StorageItemAfterSave.class)
                .forEach(c -> {
                    try {
                        TypeDefinition.getInstance(c).newInstance().afterSave(storageItem);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        return storageItem;
    }

    private static <T> Set<T> getRandomSet(Random random, int min, int max, Collection<T> all) {

        Set<T> randomSet = new HashSet<>();

        List<T> pool = new ArrayList<>(all);

        int numTeams = min + random.nextInt(max - min);

        for (int i = 0; i < numTeams; i++) {

            if (pool.isEmpty()) {
                break;
            }

            randomSet.add(pool.remove(random.nextInt(pool.size())));
        }

        return randomSet;
    }

    // Represents the contents of skills.csv
    private static class SkillsData {

        static final String RESOURCE_FILE_PATH = "/data/skills.csv";

        Set<String> skills;

        SkillsData(Set<String> skills) {
            this.skills = skills;
        }

        Set<String> getRandomSkills(Random random, int min, int max) {
            return getRandomSet(random, min, max, skills);
        }

        static SkillsData load() throws IOException {
            Set<String> skills = new HashSet<>();

            for (String skill : ResourceFileReader.getFileLines(RESOURCE_FILE_PATH)) {
                skills.add(skill);
            }

            return new SkillsData(skills);
        }
    }

    // Represents the contents of fe/male-avatars.csv
    private static class AvatarData {

        static final String FEMALE_URLS_RESOURCE_FILE_PATH = "/data/avatars/female-avatars.csv";
        static final String MALE_URLS_RESOURCE_FILE_PATH = "/data/avatars/male-avatars.csv";

        static final String FEMALE_AVATARS_RESOURCE_DIRECTORY = "/data/avatars/female/";
        static final String MALE_AVATARS_RESOURCE_DIRECTORY = "/data/avatars/male/";

        static final int FEMALE_AVATARS_RESOURCE_DIRECTORY_FILE_COUNT = 165;
        static final int MALE_AVATARS_RESOURCE_DIRECTORY_FILE_COUNT = 165;

        Map<PersonGender, List<String>> avatarUrlsByGender;
        List<String> avatarUrls;

        AvatarData(Map<PersonGender, List<String>> avatarUrlsByGender) {

            this.avatarUrlsByGender = avatarUrlsByGender;
            this.avatarUrls = this.avatarUrlsByGender.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        }

        String getRandomAvatarUrl(Random random, PersonGender gender) {

            List<String> urlsForGender = avatarUrlsByGender.get(gender);

            if (urlsForGender == null) {
                return avatarUrls.get(random.nextInt(avatarUrls.size()));

            } else {
                return urlsForGender.get(random.nextInt(urlsForGender.size()));
            }
        }

        String getRandomAvatarResourcePath(Random random, PersonGender gender) {

            if (gender != PersonGender.FEMALE && gender != PersonGender.MALE) {
                PersonGender[] genders = { PersonGender.FEMALE, PersonGender.MALE };
                gender = genders[random.nextInt(2)];
            }

            String dirPath;
            int fileCount;

            if (gender == PersonGender.FEMALE) {
                dirPath = FEMALE_AVATARS_RESOURCE_DIRECTORY;
                fileCount = FEMALE_AVATARS_RESOURCE_DIRECTORY_FILE_COUNT;
            } else {
                dirPath = MALE_AVATARS_RESOURCE_DIRECTORY;
                fileCount = MALE_AVATARS_RESOURCE_DIRECTORY_FILE_COUNT;
            }

            return dirPath + getPaddedInt((1 + random.nextInt(fileCount - 1)), 3) + ".png";
        }

        private String getPaddedInt(int value, int padding) {

            String stringValue = String.valueOf(value);
            int valueLength = stringValue.length();

            if (valueLength >= padding) {
                return stringValue;

            } else {
                char[] zeros = new char[padding - valueLength];
                Arrays.fill(zeros, '0');

                return String.valueOf(zeros) + stringValue;
            }
        }

        static AvatarData load() throws IOException {
            Map<PersonGender, List<String>> avatarUrlsByGender = new HashMap<>();
            avatarUrlsByGender.put(PersonGender.FEMALE, ResourceFileReader.getFileLines(FEMALE_URLS_RESOURCE_FILE_PATH));
            avatarUrlsByGender.put(PersonGender.MALE, ResourceFileReader.getFileLines(MALE_URLS_RESOURCE_FILE_PATH));
            return new AvatarData(avatarUrlsByGender);
        }

        private static List<String> getPickafaceAvatarUrls(int desiredAmount, int maxPages) throws IOException {

            List<String> avatarUrls = new ArrayList<>();

            final String urlFormat = "http://pickaface.net/explore/avatars/page-%d.html";

            int page = 2;

            int pageCount = 0;

            main:
            while (avatarUrls.size() < desiredAmount && pageCount < maxPages) {

                Document doc;
                try {
                    doc = jsoupParseSsl(String.format(urlFormat, page), 10000);

                } catch (IOException e) {

                    if (avatarUrls.isEmpty()) {
                        throw e;
                    } else {
                        return avatarUrls;
                    }
                }

                for (Element gridElement : doc.select(".thumb_grid")) {

                    for (Element imgElement : gridElement.select("img")) {

                        String avatarUrl = imgElement.attr("src");

                        if (avatarUrl != null) {
                            avatarUrls.add(avatarUrl);

                            if (avatarUrls.size() >= desiredAmount) {
                                break main;
                            }
                        }
                    }
                }

                try {
                    Thread.sleep(ObjectUtils.jitterAbsolutely(5000, 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                page++;
                pageCount++;
            }

            return avatarUrls;
        }

        private static Document jsoupParseSsl(String url, int timeout) throws IOException {

            AtomicReference<IOException> exceptionRef = new AtomicReference<>();

            Document doc = executeWithSslSecurityDisabled(() -> {
                try {
                    return Jsoup.parse(new URL(url), timeout);
                } catch (IOException e) {
                    exceptionRef.set(e);
                    return null;
                }
            });

            if (doc == null) {
                throw exceptionRef.get();
            }

            return doc;
        }

        private static <T> T executeWithSslSecurityDisabled(Supplier<T> supplier) {

            T supplierValue;

            final String enableSniExtensionProp = "jsse.enableSNIExtension";

            String originalProp = System.getProperty(enableSniExtensionProp);
            SSLSocketFactory originalSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
            HostnameVerifier originalVerifier = HttpsURLConnection.getDefaultHostnameVerifier();

            try {
                System.setProperty(enableSniExtensionProp, "false");

                SSLContext sc = SSLContext.getInstance("SSL");

                sc.init(null, new TrustManager[] {
                        new X509TrustManager() {
                            @Override
                            public X509Certificate[] getAcceptedIssuers() {
                                return null; // Not relevant.
                            }
                            @Override
                            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                                // Do nothing. Just allow them all.
                            }
                            @Override
                            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                                // Do nothing. Just allow them all.
                            }
                        }
                }, new SecureRandom());

                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                HttpsURLConnection.setDefaultHostnameVerifier((h, s) -> true);

                supplierValue = supplier.get();

            } catch (GeneralSecurityException e) {
                throw new ExceptionInInitializerError(e);

            } finally {

                if (originalProp != null) {
                    System.setProperty(enableSniExtensionProp, originalProp);
                } else {
                    System.clearProperty(enableSniExtensionProp);
                }

                HttpsURLConnection.setDefaultSSLSocketFactory(originalSocketFactory);
                HttpsURLConnection.setDefaultHostnameVerifier(originalVerifier);
            }

            return supplierValue;
        }
    }

    // Represents the contents of fe/male-names.csv
    private static class PersonNameData {

        static final String FEMALE_RESOURCE_FILE_PATH = "/data/female-names.csv";
        static final String MALE_RESOURCE_FILE_PATH = "/data/male-names.csv";

        Map<PersonGender, List<PersonName>> personNamesByGender;

        Map<PersonGender, List<PersonName>> availablePersonNamesByGender;

        PersonNameData(List<PersonName> personNames) {

            this.personNamesByGender = new HashMap<>();
            this.availablePersonNamesByGender = new HashMap<>();

            for (PersonName personName : personNames) {
                personNamesByGender.computeIfAbsent(personName.getGender(), e -> new ArrayList<>()).add(personName);
                availablePersonNamesByGender.computeIfAbsent(personName.getGender(), e -> new ArrayList<>()).add(personName);
            }
        }

        PersonName getUniqueRandomPersonName(Random random) {
            return getUniqueRandomPersonName(random, PersonGender.UNKNOWN);
        }

        PersonName getUniqueRandomPersonName(Random random, PersonGender gender) {
            return getRandomPersonName(random, gender, true);
        }

        PersonName getRandomPersonName(Random random) {
            return getRandomPersonName(random, PersonGender.UNKNOWN);
        }

        PersonName getRandomPersonName(Random random, PersonGender gender) {
            return getRandomPersonName(random, gender, false);
        }

        private PersonName getRandomPersonName(Random random, boolean isUnique) {
            return getRandomPersonName(random, PersonGender.UNKNOWN, isUnique);
        }

        private PersonName getRandomPersonName(Random random, PersonGender gender, boolean isUnique) {

            Map<PersonGender, List<PersonName>> all;
            if (isUnique) {
                all = availablePersonNamesByGender;

            } else {
                all = personNamesByGender;
            }

            List<PersonName> namesByGender = all.get(gender);

            if (namesByGender != null) {

                if (isUnique) {
                    return namesByGender.remove(random.nextInt(namesByGender.size()));
                } else {
                    return namesByGender.get(random.nextInt(namesByGender.size()));
                }

            } else {
                List<PersonName> concat = all.values().stream().flatMap(Collection::stream).collect(Collectors.toList());

                PersonName selected = concat.get(random.nextInt(concat.size()));

                if (isUnique) {
                    availablePersonNamesByGender.values().forEach(c -> c.remove(selected));
                }

                return selected;
            }
        }

        static PersonNameData load() throws IOException {

            List<PersonName> personNames = new ArrayList<>();

            Map<PersonGender, List<String>> names = new HashMap<>();
            names.put(PersonGender.FEMALE, ResourceFileReader.getFileLines(FEMALE_RESOURCE_FILE_PATH));
            names.put(PersonGender.MALE, ResourceFileReader.getFileLines(MALE_RESOURCE_FILE_PATH));

            for (Map.Entry<PersonGender, List<String>> entry : names.entrySet()) {
                for (String name : entry.getValue()) {
                    String[] parts = StringUtils.fromCsv(name);

                    if (parts.length >= 2) {
                        String first = parts[0].trim();
                        String last = parts[1].trim();

                        if (!StringUtils.isBlank(first) && !StringUtils.isBlank(last)) {
                            personNames.add(new PersonName(first, last, entry.getKey()));
                        }
                    }
                }
            }

            return new PersonNameData(personNames);
        }
    }

    private enum PersonGender {
        MALE, FEMALE, UNKNOWN
    }

    private static class PersonName {

        String firstName;
        String lastName;
        PersonGender gender;

        PersonName(String firstName, String lastName, PersonGender gender) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
        }

        String getFirstName() {
            return firstName;
        }

        String getLastName() {
            return lastName;
        }

        PersonGender getGender() {
            return gender;
        }

        String getEmailAddress(String domain) {
            return StringUtils.toNormalized(getFirstName()) + "." + StringUtils.toNormalized(getLastName()) + "@" + domain;
        }

        String getSlackId(Random random) {
            return firstName.substring(0, 1).toLowerCase() + lastName.toLowerCase() + (100 + random.nextInt(999 - 100));
        }

        @Override
        public int hashCode() {
            return ObjectUtils.hashCode(firstName, lastName, gender);
        }

        @Override
        public boolean equals(Object obj) {

            if (obj instanceof PersonName) {
                PersonName other = (PersonName) obj;

                return ObjectUtils.equals(firstName, other.firstName)
                        && ObjectUtils.equals(lastName, other.lastName)
                        && ObjectUtils.equals(gender, other.gender);
            }

            return false;
        }

        @Override
        public String toString() {
            return gender + " " + firstName + " " + lastName;
        }
    }

    // Represents the contents of area-codes.json
    private static class AreaCodeData {

        static final String RESOURCE_FILE_PATH = "/data/area-codes.json";

        Map<String, List<Integer>> areaCodesByState;
        List<Integer> areaCodes;

        AreaCodeData(Map<String, List<Number>> areaCodesByState) {
            Map<String, List<Integer>> map = new HashMap<>();
            areaCodesByState.forEach((k, v) -> map.put(k, v.stream().map(n -> ObjectUtils.to(Integer.class, n)).collect(Collectors.toList())));
            this.areaCodesByState = map;
            this.areaCodes = this.areaCodesByState.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        }

        Integer getRandomAreaCode(Random random) {
            return areaCodes.get(random.nextInt(areaCodes.size()));
        }

        Integer getRandomAreaCodeForState(Random random, String state) {
            List<Integer> areaCodesForState = state != null ? areaCodesByState.get(state) : null;

            if (areaCodesForState == null) {
                return getRandomAreaCode(random);
            } else {
                return areaCodesForState.get(random.nextInt(areaCodesForState.size()));
            }
        }

        String getRandomPhoneNumber(Random random) {
            return getRandomPhoneNumberForState(random, null);
        }

        String getRandomPhoneNumberForState(Random random, String state) {
            return getRandomAreaCodeForState(random, state) + "" + (100 + random.nextInt(999 - 100)) + "" + (1000 + random.nextInt(9999 - 1000));
        }

        static AreaCodeData load() throws IOException {
            // {"data":{"Alabama":[205,251,256,334,938],"Alaska":[907]
            Map<String, Object> areaCodesDataMap = ResourceFileReader.getFileAsJsonMap(RESOURCE_FILE_PATH);
            @SuppressWarnings("unchecked")
            Map<String, List<Number>> areaCodesByState = (Map<String, List<Number>>) areaCodesDataMap.get("data");
            return new AreaCodeData(areaCodesByState);
        }
    }

    // Represents the contents of street-names.json
    private static class StreetData {

        static final String RESOURCE_FILE_PATH = "/data/street-names.json";

        List<String> suffixes;
        List<String> suffixesAbbreviated;
        List<String> names;

        StreetData(List<String> suffixes, List<String> suffixesAbbreviated, List<String> names) {
            this.suffixes = suffixes;
            this.suffixesAbbreviated = suffixesAbbreviated;
            this.names = names;
        }

        String getRandomStreet(Random random) {
            return getRandomStreet(random, false);
        }

        String getRandomStreetAbbreviated(Random random) {
            return getRandomStreet(random, true);
        }

        String getRandomStreet(Random random, boolean abbreviated) {

            int lowerNumberBound = 1;
            int upperNumberBound = 20000;
            Integer number = random.nextInt(upperNumberBound - lowerNumberBound) + lowerNumberBound;
            String name = names.get(random.nextInt(names.size()));
            String type = abbreviated ? suffixesAbbreviated.get(random.nextInt(suffixesAbbreviated.size())) : suffixes.get(random.nextInt(suffixes.size()));

            return number + " " + name + " " + type;
        }

        static StreetData load() throws IOException {

            //{"types":["Street"],"typesAbbr":["St."],"names": ["Acacia"]}
            Map<String, Object> streetDataMap = ResourceFileReader.getFileAsJsonMap(RESOURCE_FILE_PATH);

            List<String> types = ObjectUtils.to(new TypeReference<List<String>>() { }, streetDataMap.get("types"));
            List<String> typesAbbreviated = ObjectUtils.to(new TypeReference<List<String>>() { }, streetDataMap.get("typesAbbr"));
            List<String> names = ObjectUtils.to(new TypeReference<List<String>>() { }, streetDataMap.get("names"));

            return new StreetData(types, typesAbbreviated, names);
        }
    }

    // Represents the contents of city-state-zip.json
    private static class CityStateZipData {

        static final String RESOURCE_FILE_PATH = "/data/city-state-zip.json";

        List<CityStateZip> cityStateZips;

        CityStateZipData(List<CityStateZip> cityStateZips) {
            this.cityStateZips = cityStateZips;
        }

        CityStateZip getRandomCityStateZip(Random random) {
            return cityStateZips.get(random.nextInt(cityStateZips.size()));
        }

        static CityStateZipData load() throws IOException {
            List<CityStateZip> cityStateZips = new ArrayList<>();

            // {"data":[{"name":"01020","detail":"Chicopee, MA"},{"name":"01040","detail":"Holyoke, MA"}
            Map<String, Object> cityStateZipsMap = ResourceFileReader.getFileAsJsonMap(CityStateZipData.RESOURCE_FILE_PATH);

            @SuppressWarnings("unchecked")
            List<Object> cityStateZipsList = (List<Object>) cityStateZipsMap.get("data");

            for (Object cityStateZipItem : cityStateZipsList) {

                Object name = CollectionUtils.getByPath(cityStateZipItem, "name");
                Object detail = CollectionUtils.getByPath(cityStateZipItem, "detail");

                String zip = ObjectUtils.to(String.class, name);
                String cityState = ObjectUtils.to(String.class, detail);
                String[] cityStateParts = cityState.split(", ");

                if (cityStateParts.length == 2) {
                    String city = cityStateParts[0];
                    String state = cityStateParts[1];

                    cityStateZips.add(new CityStateZip(city, state, zip));
                }
            }

            return new CityStateZipData(cityStateZips);
        }
    }

    private static class CityStateZip {

        String city;
        String stateAbbreviation;
        String zip;

        CityStateZip(String city, String stateAbbreviation, String zip) {
            this.city = city;
            this.stateAbbreviation = stateAbbreviation;
            this.zip = zip;
        }

        String getCity() {
            return city;
        }

        String getStateAbbreviation() {
            return stateAbbreviation;
        }

        String getZip() {
            return zip;
        }
    }
}
