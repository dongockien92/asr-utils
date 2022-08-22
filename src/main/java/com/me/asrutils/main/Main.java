package com.me.asrutils.main;

import com.me.asrutils.model.Action;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, String> idToName = AsrRuleFetcher.fetchAllRules();
        Map<String, Action> idToAction = AsrRuleGetter.getRules();
        System.out.println("RESULT:");
        for (Map.Entry<String, String> entry : idToName.entrySet()) {
            String ruleId = entry.getKey();
            String ruleName = entry.getValue();
            Action action = idToAction.get(ruleId);
            System.out.println("Rule : " + ruleName + " , Action : " + action);
        }
    }
}
