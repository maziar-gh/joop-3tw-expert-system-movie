package com.codecool;

import java.util.*;

public class ESProvider {

    FactParser factParser;
    RuleParser ruleParser;
    private List<Question> rules;
    private List<Fact> facts;

    private Map<String, Boolean> qa = new HashMap<>();
    Answer answer;
    private Iterator<Question> questionIterator;
    private Iterator<Fact> factIterator;

    private RuleRepository ruleRepository;


    public ESProvider(FactParser factParser, RuleParser ruleParser) {
        this.factParser = factParser;
        this.ruleParser = ruleParser;
        ruleRepository = ruleParser.getRuleRepository();
        //facts = factRepository.getFactList();
        rules = ruleRepository.getRuleList();
        this.questionIterator = ruleRepository.getIterator();
    }

    public void collectAnswers() {
        Scanner scanner = new Scanner(System.in);
        String line;
        answer = new Answer();

        while (questionIterator.hasNext()) {
            Question question = questionIterator.next();
            System.out.println(question.getQuestion());
            line = scanner.nextLine().toLowerCase();

            while (true){
                if (("yes".equals(line) || "no".equals(line))) {
                    break;
                }
                else{
                    System.out.println("Wrong input! Please type yes or no for the question!");
                    System.out.println(question.getQuestion());
                    line = scanner.nextLine().toLowerCase();
                }
            }

            qa.put(question.getId(), question.getEvaluatedAnswer(line));
        }

    }

    public boolean getAnswerByQuestion(String questionId) {
        boolean answer;
        answer = qa.get(questionId);
        return answer;
    }

    public String evaluate() {
        String string = "";
        return string;
    }
}
