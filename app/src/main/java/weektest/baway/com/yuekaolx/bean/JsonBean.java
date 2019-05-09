package weektest.baway.com.yuekaolx.bean;

import java.util.List;

public class JsonBean {
    List<results> result;

    public List<results> getResult() {
        return result;
    }

    public void setResult(List<results> result) {
        this.result = result;
    }

    public class results{
        public String imageUrl;
        public String summary;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }
}
