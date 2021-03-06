package com.example.john.simulatesms.util;

import android.net.Uri;

import com.example.john.simulatesms.provider.GroupProvider;

/**
 * Created by John on 2016/11/21.
 */

public class ConstantUtil {
    public interface URI {
        Uri CONVERSATION_URL = Uri.parse("content://sms/conversations");
        Uri CONVERSATION_DELETE_URL = Uri.parse("content://sms");
        Uri GROUP_URI = Uri.withAppendedPath(GroupProvider.BASE_URI, "groups");
        Uri GROUP_MAPPING_THREAD_URI = Uri.withAppendedPath(GroupProvider.BASE_URI, "group_mapping_thread");
    }

    public interface SmsType {
        int RECEIVER = 1;
        int SENDER = 2;
    }

    public interface Sms {
        String CENTER_SMS = "+8613800557500";
    }
}
