package com.mustafa.constants;

public class RestApiUrls {
    private static final String VERSION = "/v1";
    private static final String API = "/api";
    private static final String TEST = "/test";
    private static final String DEV = "/dev";

    private static final String ROOT = DEV + VERSION;

    public static final String AUTH = ROOT + "/auth";
    public static final String USER = ROOT + "/user";
    public static final String OTEL = ROOT + "/otel";
    public static final String OZELLIK = ROOT + "/ozellik";

    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String SAVE = "/save";

    public static final String ACTIVATE_STATUS = "/activateStatus";
    public static final String UPDATE = "/update";
    public static final String ADD_FAVORITE = "/add-favorite";
    public static final String FAVORI_OTELS = "/favorite-otels";
    public static final String FIND_ALL = "/find-all";
    public static final String FILTER_LIST = "/filter-list";
    public static final String FIND_SEARCH = "/find-search";
    public static final String GET_ALL_PARENTS = "/get-all-parents";



    public static final String FIND_BY_TOKEN = "/find-by-token";
    public static final String FIND_BY_ID = "/find-by-id";






}
