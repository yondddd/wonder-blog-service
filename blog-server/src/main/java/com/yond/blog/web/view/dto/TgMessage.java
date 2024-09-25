package com.yond.blog.web.view.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Telegram新消息
 *
 * @Author: Yond
 */
public class TgMessage {
    @JsonProperty("update_id")
    private String updateId;
    private Message message;
    
    public TgMessage() {
    }
    
    public String getUpdateId() {
        return this.updateId;
    }
    
    public Message getMessage() {
        return this.message;
    }
    
    @JsonProperty("update_id")
    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }
    
    public void setMessage(Message message) {
        this.message = message;
    }
    
    public String toString() {
        return "TgMessage(updateId=" + this.getUpdateId() + ", message=" + this.getMessage() + ")";
    }
    
    public class Message {
        @JsonProperty("message_id")
        private String messageId;
        private From from;
        private Chat chat;
        private String date;
        private String text;
        
        public Message() {
        }
        
        public String getMessageId() {
            return this.messageId;
        }
        
        public From getFrom() {
            return this.from;
        }
        
        public Chat getChat() {
            return this.chat;
        }
        
        public String getDate() {
            return this.date;
        }
        
        public String getText() {
            return this.text;
        }
        
        @JsonProperty("message_id")
        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }
        
        public void setFrom(From from) {
            this.from = from;
        }
        
        public void setChat(Chat chat) {
            this.chat = chat;
        }
        
        public void setDate(String date) {
            this.date = date;
        }
        
        public void setText(String text) {
            this.text = text;
        }
        
        public String toString() {
            return "TgMessage.Message(messageId=" + this.getMessageId() + ", from=" + this.getFrom() + ", chat=" + this.getChat() + ", date=" + this.getDate() + ", text=" + this.getText() + ")";
        }
        
        public class From {
            private String id;
            @JsonProperty("is_bot")
            private Boolean isBot;
            @JsonProperty("first_name")
            private String firstName;
            private String username;
            @JsonProperty("language_code")
            private String languageCode;
            
            public From() {
            }
            
            public String getId() {
                return this.id;
            }
            
            public Boolean getIsBot() {
                return this.isBot;
            }
            
            public String getFirstName() {
                return this.firstName;
            }
            
            public String getUsername() {
                return this.username;
            }
            
            public String getLanguageCode() {
                return this.languageCode;
            }
            
            public void setId(String id) {
                this.id = id;
            }
            
            @JsonProperty("is_bot")
            public void setIsBot(Boolean isBot) {
                this.isBot = isBot;
            }
            
            @JsonProperty("first_name")
            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }
            
            public void setUsername(String username) {
                this.username = username;
            }
            
            @JsonProperty("language_code")
            public void setLanguageCode(String languageCode) {
                this.languageCode = languageCode;
            }
            
            public String toString() {
                return "TgMessage.Message.From(id=" + this.getId() + ", isBot=" + this.getIsBot() + ", firstName=" + this.getFirstName() + ", username=" + this.getUsername() + ", languageCode=" + this.getLanguageCode() + ")";
            }
        }
        
        public class Chat {
            private String id;
            @JsonProperty("first_name")
            private String firstName;
            private String username;
            private String type;
            
            public Chat() {
            }
            
            public String getId() {
                return this.id;
            }
            
            public String getFirstName() {
                return this.firstName;
            }
            
            public String getUsername() {
                return this.username;
            }
            
            public String getType() {
                return this.type;
            }
            
            public void setId(String id) {
                this.id = id;
            }
            
            @JsonProperty("first_name")
            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }
            
            public void setUsername(String username) {
                this.username = username;
            }
            
            public void setType(String type) {
                this.type = type;
            }
            
            public String toString() {
                return "TgMessage.Message.Chat(id=" + this.getId() + ", firstName=" + this.getFirstName() + ", username=" + this.getUsername() + ", type=" + this.getType() + ")";
            }
        }
    }
}
