package com.xyk.interfaceTest.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-10-22 10:30
 * @Description
 */
public class WrongTopic {
    private String topicId;
    private String topicSetId;
    private String scoreRate;
    private String contentHtml;
    private String topicSetName;
    private List<String> knowledgeNames;
    private String examName;
    private String analysisHtml;
    private String answerHtml;
    private Double userScore;

    public String getTopicId() {
        return topicId;
    }

    public WrongTopic setTopicId(String topicId) {
        this.topicId = topicId;
        return this;
    }

    public String getTopicSetId() {
        return topicSetId;
    }

    public WrongTopic setTopicSetId(String topicSetId) {
        this.topicSetId = topicSetId;
        return this;
    }

    public String getScoreRate() {
        return scoreRate;
    }

    public WrongTopic setScoreRate(String scoreRate) {
        this.scoreRate = scoreRate;
        return this;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public WrongTopic setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
        return this;
    }

    public String getTopicSetName() {
        return topicSetName;
    }

    public WrongTopic setTopicSetName(String topicSetName) {
        this.topicSetName = topicSetName;
        return this;
    }

    public List<String> getKnowledgeNames() {
        return knowledgeNames;
    }

    public WrongTopic setKnowledgeNames(List<String> knowledgeNames) {
        this.knowledgeNames = knowledgeNames;
        return this;
    }

    public String getExamName() {
        return examName;
    }

    public WrongTopic setExamName(String examName) {
        this.examName = examName;
        return this;
    }

    public String getAnalysisHtml() {
        return analysisHtml;
    }

    public WrongTopic setAnalysisHtml(String analysisHtml) {
        this.analysisHtml = analysisHtml;
        return this;
    }

    public String getAnswerHtml() {
        return answerHtml;
    }

    public WrongTopic setAnswerHtml(String answerHtml) {
        this.answerHtml = answerHtml;
        return this;
    }

    public Double getUserScore() {
        return userScore;

    }

    public WrongTopic setUserScore(Double userScore) {
        this.userScore = userScore;
        return this;
    }

    @Override
    public String toString() {
        return "WrongTopic{" +
                "topicId='" + topicId + '\'' +
                ", topicSetId='" + topicSetId + '\'' +
                ", scoreRate='" + scoreRate + '\'' +
                ", contentHtml='" + contentHtml + '\'' +
                ", topicSetName='" + topicSetName + '\'' +
                ", knowledgeNames=" + knowledgeNames +
                ", examName='" + examName + '\'' +
                ", analysisHtml='" + analysisHtml + '\'' +
                ", answerHtml='" + answerHtml + '\'' +
                ", userScore=" + userScore +
                '}';
    }
}
