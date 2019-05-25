package com.example.drawnig_apps.letters.assets;

public class GameInfos {
        String test;
        String app_id;
        private String child_id;
        private String user_id;
        private String exercise_id;
        private String level_id;
        String updated_at;
        String created_at;
        private String game_date_id;
        private String successful_attempts;
        private String failed_attempts;
        private String min_time_succeed_sec;
        private String avg_time_succeed_sec;
        private String longitude;
        private String latitude;
        private String device;
        private String flag;
        public GameInfos(String app_id, String child_id, String user_id, String exercise_id, String level_id, String updated_at, String created_at, String game_date_id, String successful_attempts, String failed_attempts, String min_time_succeed_sec, String avg_time_succeed_sec, String longitude, String latitude, String device, String flag) {
            this.app_id = app_id;
            this.child_id = child_id;
            this.user_id = user_id;
            this.exercise_id = exercise_id;
            this.level_id = level_id;
            this.updated_at = updated_at;
            this.created_at = created_at;
            this.game_date_id = game_date_id;
            this.successful_attempts = successful_attempts;
            this.failed_attempts = failed_attempts;
            this.min_time_succeed_sec = min_time_succeed_sec;
            this.avg_time_succeed_sec = avg_time_succeed_sec;
            this.longitude = longitude;
            this.latitude = latitude;
            this.device = device;
            this.flag = flag;
        }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
