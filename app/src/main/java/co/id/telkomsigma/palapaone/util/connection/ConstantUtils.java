package co.id.telkomsigma.palapaone.util.connection;

/**
 * Created by ramli on 23/04/2017.
 */

public interface ConstantUtils {

    interface URL {
        String SERVER = "http://palapaone.id/icw/";
        String DICTIONARY = SERVER + "api/home/lang/";
        String BANNER = SERVER + "api/event/banner/";
        String ALLSPEAKER = SERVER + "api/speaker/allspeaker/";
        String SPEAKER = SERVER + "api/speaker/getcontent/";
        String MATERI = SERVER + "api/speaker/getmateri";
        String MATERIBYSPEAKER = SERVER + "api/speaker/getmateribyspeaker/";
        String MEDIA = SERVER + "api/mediacenter/content/";
        String PARTNER = SERVER + "api/partner/content/";
        String CALL_CENTER = SERVER + "api/callcenter/content/";
        String GALLERY = SERVER + "api/gallery/getcontent/";
        String EXPO = SERVER + "api/expo/getexpo/";
        String CAT_EXPO = SERVER + "api/expo/getcategory/";
        String VERSION = SERVER + "api_version/checkVersion";
        String LOGIN = SERVER + "api/login/auth";
        String PROFILE_EDIT = SERVER + "api/profile/update";
        String FEEDBACK = SERVER + "api/feedback/content/";
        String SEND_FEEDBACK = SERVER + "api/feedback/submit";
        String AGENDA = SERVER + "api/event/getagenda/";
        String EVENT = SERVER + "api/event/getevent/";
        String RUNDOWN = SERVER + "api/schedule/rundown/";
        String SEND_ASKING = SERVER + "api/asking/askingadd/";
        String NOTIFICATION = SERVER + "api/notification/content/";
        String LOGOUT = SERVER + "api_login/logout/";
    }

    interface DICTIONARY {
        String TAG_USERNAME = "username";
        String TAG_PASSWORD = "password";
        String TAG_LOGIN = "login";
        String TAG_MSG = "msg";
        String TAG_MENU_1 = "menu1";
        String TAG_MENU_2 = "menu2";
        String TAG_MENU_3 = "menu3";
        String TAG_MENU_4 = "menu4";
        String TAG_MENU_5 = "menu5";
        String TAG_MENU_6 = "menu6";
        String TAG_MENU_7 = "menu7";
        String TAG_MENU_8 = "menu8";
        String TAG_MENU_9 = "menu9";
    }

    interface VERSION {
        String TAG_TITLE = "version";
        String TAG_ID = "version_id";
        String TAG_NUMBER = "version_number";
        String TAG_URL = "version_url";
        String TAG_NOTE = "version_note";
        String TAG_DATE = "version_date";
    }

    interface LOGIN {
        String TAG_USERID = "user_id";
        String TAG_USERNAME = "username";
        String TAG_NAME = "user_full_name";
        String TAG_EMAIL = "user_email";
        String TAG_PHONE = "user_phone";
        String TAG_ABOUT = "user_about";
        String TAG_QUOTE = "user_quote";
        String TAG_JOB = "user_job";
        String TAG_ROLE = "role_id";
        String TAG_EVENTID = "event_id";
        String TAG_EVENT = "event_name";
        String TAG_PARENT = "parent_id";
        String TAG_NATIONAL_ID = "nationality_id";
        String TAG_NATIONAL_NAME = "nationality_name";
        String TAG_OFFICE = "user_office";
        String TAG_SUCCESS = "success";
    }

    interface BANNER {
        String TAG_TITLE = "banner";
        String TAG_ID = "banner_id";
        String TAG_IMAGE = "banner_image";
        String TAG_EVENT = "event_id";
        //String TAG_EVENT = "banner_event";
        String TAG_URL = "banner_url";
    }

    interface SPEAKER {
        String TAG_TITLE = "speaker";
        String TAG_ID = "speaker_id";
        String TAG_NAME = "speaker_name";
        String TAG_PHOTO = "speaker_photo";
        String TAG_EMAIL = "speaker_email";
        String TAG_PHONE = "speaker_phone";
        String TAG_QUOTE = "speaker_quotes";
        String TAG_TOPIC = "speaker_topic";
        String TAG_NATIONAL = "nationality_name";
        String TAG_EVENT = "event_name";
        String TAG_JOB = "speaker_job";
        String TAG_DESC = "speaker_desc";
        String TAG_ABOUT = "speaker_about";
    }

    interface MATERI {
        String TAG_TITLE = "materi";
        String TAG_ID = "material_id";
        String TAG_NAME = "material_title";
        String TAG_FILE = "material_file";
        String TAG_DESC = "material_desc";
        String TAG_SPEAK_ID = "speaker_id";
        String TAG_SPEAK_NAME = "speaker_name";
    }

    interface MEDIA {
        String TAG_TITLE = "media";
        String TAG_ID = "media_center_id";
        String TAG_USERID = "media_center_editor";
        String TAG_USER = "user_full_name";
        String TAG_FILE = "media_center_file";
        String TAG_NAME = "media_center_title";
        String TAG_DATE = "created_date";
        String TAG_EVENT = "event_id";
    }

    interface PARTNER {
        String TAG_TITLE = "parent";
        String TAG_ID = "partner_id";
        String TAG_NAME = "partner_name";
        String TAG_LOGO = "partner_logo";
        String TAG_DESC = "partner_desc";
        String TAG_ADDRESS = "partner_address";
        String TAG_PHONE = "partner_phone";
        String TAG_URL = "partner_url";
        String TAG_EVENTID = "event_id";
        String TAG_DATE = "created_date";
        String TAG_BY = "created_by";
    }

    interface CALL_CENTER {
        String TAG_TITLE = "call_center";
        String TAG_ID = "call_center_id";
        String TAG_NUMBER = "call_center_number";
        String TAG_NAME = "call_center_name";
        String TAG_EVENTID = "event_id";
        String TAG_TYPE = "call_center_type";
        String TAG_IMAGE = "call_center_image";
        String TAG_LAYOUT = "call_center_layout";
        String TAG_LONGITUDE = "call_center_longitude";
        String TAG_LATITUDE = "call_center_latitude";
    }

    interface GALLERY {
        String TAG_TITLE = "gallery";
        String TAG_ID = "gallery_id";
        String TAG_URL = "gallery_img_url";
        String TAG_TESTI = "gallery_testimoni";
        String TAG_CAPTION = "gallery_caption";
        String TAG_STATUS = "gallery_status";
        String TAG_BY = "gallery_posted_by";
        String TAG_DATE = "created_date";
        String TAG_EVENTID = "event_id";
    }

    interface CAT_EXPO {
        String TAG_TITLE = "category";
        String TAG_ID = "expo_category_id";
        String TAG_NAME = "expo_category_name";
    }

    interface EXPO {
        String TAG_TITLE = "expo";
        String TAG_ID = "expo_id";
        String TAG_NAME = "expo_name";
        String TAG_DESC = "expo_desc";
        String TAG_PROD = "expo_image_product";
        String TAG_MAP = "expo_image_map";
        String TAG_LOC = "expo_location";
    }

    interface AGENDA {
        String TAG_TITLE = "agenda";
        String TAG_ID = "agenda_id";
        String TAG_NAME = "agenda_name";
        String TAG_DATE = "agenda_date";
        String TAG_EVENT = "event_id";
        String TAG_DAY = "day_x";
    }

    interface FEEDBACK {
        String TAG_TITLE = "feedback";
        String TAG_ID = "feedback_id";
        String TAG_TYPE = "feedback_type";
        String TAG_QUESTION = "feedback_question";
        String TAG_EVENTID = "event_id";
        String TAG_DATE = "created_date";
    }

    interface SUBMIT_FB {
        String TAG_TITLE = "feedback";
        String TAG_ID = "feedback_id";
        String TAG_SCORE = "feedback_type";
        String TAG_TEXT = "feedback_question";
        String TAG_BY = "event_id";
    }

    interface EVENT {
        String TAG_TITLE = "event";
        String TAG_ID = "event_id";
        String TAG_NAME = "event_name";
        String TAG_LOCA = "event_location";
        String TAG_START = "start_date";
        String TAG_END = "end_date";
        String TAG_PARENT = "event_parent_id";
        String TAG_LATIT = "event_latitude";
        String TAG_LONGI = "event_longitude";
        String TAG_THEME = "event_theme";
    }

    interface RUNDOWN {
        String TAG_TITLE = "rundown";
        String TAG_ID = "rundown_id";
        String TAG_NAME = "rundown_name";
        String TAG_START = "rundown_time_start";
        String TAG_END = "rundown_time_end";
        String TAG_DESC = "rundown_desc";
        String TAG_PLACE = "rundown_place";
        String TAG_LAYOUT = "rundown_layout";
    }

    interface ASKING {
        String TAG_BY = "asking_by";
        String TAG_QUESTION = "asking_question";
        String TAG_STATUS = "asking_status";
        String TAG_EVENTID = "event_id";
        String TAG_MATERIID = "materi_id";
        String TAG_STAT = "status";
    }

    interface NOTIF {
        String TAG_ID = "notification_id";
        String TAG_HEAD = "notification";
        String TAG_TITLE = "notification_title";
        String TAG_TEXT = "notification_text";
        String TAG_TYPE = "notification_type";
        String TAG_ATTACH = "notification_attachment";
        String TAG_CATEGORY = "notif_category_id";
        String TAG_EVENTID = "event_id";
        String TAG_DATE = "created_date";
    }
}