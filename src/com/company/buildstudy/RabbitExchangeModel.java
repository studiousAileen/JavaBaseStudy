package com.company.buildstudy;

import java.util.concurrent.TimeUnit;

public class RabbitExchangeModel {
    private String ServerUrl;
    private int Port;
    private String UserName;
    private String Pwd;
    private String QueueName;
    private String ExchangeName;
    private String Type;
    private String ConsumerKey;
    private String ProducerKey;
    private boolean isDLX;
    private String DLE;
    private String DLK;
    private TimeUnit retryTimeUnit;
    private long retryDelaytime;
    private int retries;
    private boolean isRetried;

    private RabbitExchangeModel(Builder builder) {
        this.ServerUrl = builder.ServerUrl;
        this.Port = builder.Port;
        this.UserName = builder.UserName;
        this.Pwd = builder.Pwd;
        this.QueueName = builder.QueueName;
        this.ExchangeName = builder.ExchangeName;
        this.Type = builder.Type;
        this.ConsumerKey = builder.ConsumerKey;
        this.ProducerKey = builder.ProducerKey;
        this.isDLX = builder.isDLX;
        this.DLE = builder.DLE;
        this.DLK = builder.DLK;
        this.retryTimeUnit = builder.retryTimeUnit;
        this.retryDelaytime = builder.retryDelaytime;
        this.retries = builder.retries;
        this.isRetried = builder.isRetried;
    }

    public static class Builder {
        private String ServerUrl;
        private int Port;
        private String UserName;
        private String Pwd;
        private String QueueName;
        private String ExchangeName;
        private String Type;
        private String ConsumerKey;
        private String ProducerKey;
        private boolean isDLX;
        private String DLE;
        private String DLK;
        private TimeUnit retryTimeUnit;
        private long retryDelaytime;
        private int retries;
        private boolean isRetried;
        private boolean Durable = true;

        public Builder(String ServerUrl, int Port, String UserName, String Pwd, String ExchangeName, String Type) {
            this.ServerUrl = ServerUrl;
            this.Port = Port;
            this.UserName = UserName;
            this.Pwd = Pwd;
            this.ExchangeName = ExchangeName;
            this.Type = Type;
        }
        public Builder QueueName(String QueueName) {
            this.QueueName = QueueName;
            return this;
        }
        public Builder ConsumerKey(String ConsumerKey) {
            this.ConsumerKey = ConsumerKey;
            return this;
        }

        public Builder ProducerKey(String ProducerKey) {
            this.ProducerKey = ProducerKey;
            return this;
        }

        public Builder isDLX(boolean isDLX) {
            this.isDLX = isDLX;
            return this;
        }

        public Builder DLE(String DLE) {
            this.DLE = DLE;
            return this;
        }

        public Builder DLK(String DLK) {
            this.DLK = DLK;
            return this;
        }

        public Builder retryTimeUnit(TimeUnit retryTimeUnit) {
            this.retryTimeUnit = retryTimeUnit;
            return this;
        }

        public Builder retryDelaytime(long retryDelaytime) {
            this.retryDelaytime = retryDelaytime;
            return this;
        }

        public Builder retries(int retries) {
            this.retries = retries;
            return this;
        }

        public Builder isRetried(boolean isRetried) {
            this.isRetried = isRetried;
            return this;
        }

        public Builder Durable(boolean Durable) {
            this.Durable = true;
            return this;
        }

        public RabbitExchangeModel build() {
            return new RabbitExchangeModel(this);
        }
    }

    public String getServerUrl() {
        return ServerUrl;
    }

    public int getPort() {
        return Port;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPwd() {
        return Pwd;
    }

    public String getQueueName() {
        return QueueName;
    }

    public String getExchangeName() {
        return ExchangeName;
    }

    public String getType() {
        return Type;
    }

    public String getConsumerKey() {
        return ConsumerKey;
    }

    public String getProducerKey() {
        return ProducerKey;
    }

    public boolean isDLX() {
        return isDLX;
    }

    public String getDLE() {
        return DLE;
    }

    public String getDLK() {
        return DLK;
    }

    public TimeUnit getRetryTimeUnit() {
        return retryTimeUnit;
    }

    public long getRetryDelaytime() {
        return retryDelaytime;
    }

    public int getRetries() {
        return retries;
    }

    public boolean isRetried() {
        return isRetried;
    }
}
