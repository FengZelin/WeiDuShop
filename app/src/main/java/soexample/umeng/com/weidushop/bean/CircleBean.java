package soexample.umeng.com.weidushop.bean;

import java.util.List;

/**
 * date:2019/2/21
 * author:冯泽林(asus)
 * function:
 */
public class CircleBean {

    /**
     * result : [{"commodityId":1,"content":"123456","createTime":1546655230000,"greatNum":568,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":243,"image":"http://172.17.8.100/images/small/circle_pic/2019-01-04/2419820190104202710.PNG","nickName":"杨忠","userId":217,"whetherGreat":2},{"commodityId":1,"content":"123456","createTime":1546655210000,"greatNum":97,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":242,"image":"http://172.17.8.100/images/small/circle_pic/2019-01-04/1947220190104202650.jpg","nickName":"杨忠","userId":217,"whetherGreat":2},{"commodityId":1,"content":"我同桌是傻狗","createTime":1546655175000,"greatNum":88,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":241,"image":"http://172.17.8.100/images/small/circle_pic/2019-01-04/7856420190104202615.jpg","nickName":"杨忠","userId":217,"whetherGreat":2},{"commodityId":1,"content":"123123123","createTime":1546654939000,"greatNum":55,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":240,"image":"http://172.17.8.100/images/small/circle_pic/2019-01-04/7258920190104202219.png","nickName":"杨忠","userId":217,"whetherGreat":2},{"commodityId":1,"content":"哒哒哒哒哒哒哒哒","createTime":1546556451000,"greatNum":185,"headPic":"http://172.17.8.100/images/small/head_pic/2019-01-04/20190104141318.jpg","id":239,"image":"http://172.17.8.100/images/small/circle_pic/2019-01-03/6827420190103170051.jfif","nickName":"五","userId":70,"whetherGreat":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 1
         * content : 123456
         * createTime : 1546655230000
         * greatNum : 568
         * headPic : http://172.17.8.100/images/small/default/user.jpg
         * id : 243
         * image : http://172.17.8.100/images/small/circle_pic/2019-01-04/2419820190104202710.PNG
         * nickName : 杨忠
         * userId : 217
         * whetherGreat : 2
         */

        private int commodityId;
        private String content;
        private long createTime;
        private int greatNum;
        private String headPic;
        private int id;
        private String image;
        private String nickName;
        private int userId;
        private int whetherGreat;
        private boolean isGreate;

        public boolean isGreate() {
            return isGreate;
        }

        public void setGreate(boolean greate) {
            isGreate = greate;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }
    }
}
