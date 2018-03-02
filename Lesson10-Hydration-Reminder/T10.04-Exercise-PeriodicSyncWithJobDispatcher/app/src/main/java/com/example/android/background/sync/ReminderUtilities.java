/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.background.sync;


import android.content.Context;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

public class ReminderUtilities {
    // done (15) Create three constants and one variable:
    private static final int REMINDER_INTERVAL_SECONDS = 30;
    private static final int SYNC_FLEXTIME_SECONDS = 30;
    private static final String REMINDER_JOB_TAG ="hydration_reminder_tag";

    private static boolean sIntialized;
    //  - REMINDER_INTERVAL_SECONDS should be an integer constant storing the number of seconds in 15 minutes
    //  - SYNC_FLEXTIME_SECONDS should also be an integer constant storing the number of seconds in 15 minutes
    //  - REMINDER_JOB_TAG should be a String constant, storing something like "hydration_reminder_tag"
    //  - sInitialized should be a private static boolean variable which will store whether the job
    //    has been activated or not

    // done (16) Create a synchronized, public static method called scheduleChargingReminder that takes
    synchronized public static void scheduleChargingReminder(Context context){
        if(sIntialized)
            return;
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));

        Job myJob = dispatcher.newJobBuilder()
                .setService(WaterReminderFirebaseJobService.class)
                .setTag(REMINDER_JOB_TAG)
                .setRecurring(true)
                .setLifetime(Lifetime.FOREVER)
                .setTrigger(Trigger.executionWindow(REMINDER_INTERVAL_SECONDS,SYNC_FLEXTIME_SECONDS))
                .setReplaceCurrent(true)
                .setConstraints(Constraint.DEVICE_CHARGING)
                .build();
        dispatcher.schedule(myJob);
        sIntialized = true;
    }
    // in a context. This method will use FirebaseJobDispatcher to schedule a job that repeats roughly
    // every REMINDER_INTERVAL_SECONDS when the phone is charging. It will trigger WaterReminderFirebaseJobService
    // Checkout https://github.com/firebase/firebase-jobdispatcher-android for an example
        // done (17) If the job has already been initialized, return
        // done (18) Create a new GooglePlayDriver
        // done (19) Create a new FirebaseJobDispatcher with the driver
        // done (20) Use FirebaseJobDispatcher's newJobBuilder method to build a job which:
            // - has WaterReminderFirebaseJobService as it's service Y
            // - has the tag REMINDER_JOB_TAG Y
            // - only triggers if the device is charging Y
            // - has the lifetime of the job as forever Y
            // - has the job recur Y
            // - occurs every 15 minutes with a window of 15 minutes. You can do this using a
            //   setTrigger, passing in a Trigger.executionWindow Y
            // - replaces the current job if it's already running Y
        // Finally, you should build the job.
        // done (21) Use dispatcher's schedule method to schedule the job
        // done (22) Set sInitialized to true to mark that we're done setting up the job
}
