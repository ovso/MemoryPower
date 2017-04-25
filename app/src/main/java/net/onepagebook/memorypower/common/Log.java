package net.onepagebook.memorypower.common;

import net.onepagebook.memorypower.BuildConfig;

import timber.log.Timber;

public class Log {
    public final static void d(String msg, Object... args) {
        if (BuildConfig.DEBUG) Timber.d(msg, args);
    }

    public final static void d(Throwable t) {
        if (BuildConfig.DEBUG) Timber.d(t);
    }

    public final static void d(Throwable t, String msg, Object... args) {
        if (BuildConfig.DEBUG) Timber.d(t, msg, args);
    }

    public final static void i(String msg, Object... args) {
        if (BuildConfig.DEBUG) Timber.d(msg, args);
    }

    public final static void i(Throwable t) {
        if (BuildConfig.DEBUG) Timber.d(t);
    }

    public final static void i(Throwable t, String msg, Object... args) {
        if (BuildConfig.DEBUG) Timber.d(t, msg, args);
    }

/*
    private static String buildLogMsg(String message) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(ste.getFileName());
        sb.append(" > ");
        sb.append(ste.getMethodName());
        sb.append(" > #");
        sb.append(ste.getLineNumber());
        sb.append("] ");
        sb.append(message);
        return sb.toString();
    }
*/
}