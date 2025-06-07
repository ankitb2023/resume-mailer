package com.example.resumemailer.model;

public class EmailRequest {
    private String person;
    private String company;
    private String post;
    private String email;
    private String templateKey;

    // Getters and Setters
    public String getPerson() { return person; }
    public void setPerson(String person) { this.person = person; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTemplateKey() { return templateKey; }
    public void setTemplateKey(String templateKey) { this.templateKey = templateKey; }
}