package com.tdi.onemillion;

public class sdk_Constant {

    public static final String LOG_TAG = "c9";


    public static String PREF_NAME = "C9";

    public static final String DEV_BASE_URL = "https://dev.api.curator9.com";
//    public static final String BASE_URL = "https://api.curator9.com";
    public static final String DEV_CDN_URL = "https://chuncheon.blob.core.windows.net/chuncheon/"; //(개발 CDN URL)
//        public static final String CDN_URL = "https://c9bloodpressure.azureedge.net/";  //TODO : 매체마다 변동됨(혈압)
//        public static final String DEV_CDN_URL = "https://c9ticketoffice.azureedge.net/";  //TODO : 매체마다 변동됨(매표소)
      //  public static final String CDN_URL = "https://c9tingstar.azureedge.net/";  //TODO : 매체마다 변동됨(틴스타)
    public static final String GRANT_TYPE = "client_credentials";
    public static final String CLIENT_ID = "9403d49b-dba2-4dbe-87b1-0001de30125d";
    public static final String CLIENT_SECRET = "wIh5A7aLmEYl4Mdk85Jrey8xjsKmMxfSmJ8BLg3s";
    public static final String AUTHORIZATION = "eyJpdiI6ImRna2YyMi9jWkx2RmJMcC8zQ1ZqOXc9PSIsInZhbHVlIjoiQitMZUFGWHZ5eXp4cCsyemtsa2l3Zz09IiwibWFjIjoiMGEzMjdjODNiZmI0YzZkOTI3NjhmZmQ4YTkyNTFkMzA5MzYyMzNiYmIxZjA5YzljNWIxMWI0YjQxMjkyNTQ5NyJ9";


    public static final String API_VERSION = "v1";
    public static final int MEDIA_ID = 6;  //TODO : 매체마다 변동됨 (1 = 혈압 , 2 = 골프몽, 3 = 매표소, 4 = 틴스타) , 원 밀리언 6
//            public static final String C9_KEY  = "bloodpressure";  //TODO : 매체마다 변동됨(혈압)
//    public static final String C9_KEY  = "golfzip";  //TODO : 매체마다 변동됨(골프몽)
// public static final String C9_KEY  = "ticketoffice";  //TODO : 매체마다 변동됨(매표소)
//    public static final String C9_KEY = "tingstar";  //TODO : 매체마다 변동됨(틴스타)
    public static final String C9_KEY = "onemillion";  //TODO : 매체마다 변동됨 (원밀리언)


    public static final String PLATFORM_TYPE_1 = "instagram";  //인스타그램
    public static final String PLATFORM_TYPE_2 = "facebook";  //페이스북
    public static final String PLATFORM_TYPE_3 = "youtube";  //유튜브
    public static final String PLATFORM_TYPE_4 = "naver-blog";  //네이버블로그
    public static final String PLATFORM_TYPE_5 = "twitter";  //트위터


}
