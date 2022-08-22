package com.me.asrutils.main;

import com.me.asrutils.model.Action;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
//        compare();
        buildAsrCommand();
    }

    private static void buildAsrCommand() {
        String enabled = "Enabled";
        String warn = "Warn";
        Map<String, String> idToAction = new LinkedHashMap<>();
        idToAction.put("56a863a9-875e-4185-98a7-b882c64b5ce5", warn);
        idToAction.put("7674ba52-37eb-4a4f-a9a1-f0f9a1619a2c", enabled);
        idToAction.put("d4f940ab-401b-4efc-aadc-ad5f3c50688a", enabled);
        idToAction.put("be9ba2d9-53ea-4cdc-84e5-9b1eeee46550", enabled);
        idToAction.put("5beb7efe-fd9a-4556-801d-275e5ffc04cc", enabled);
        idToAction.put("d3e037e1-3eb8-44c8-a917-57927947596d", enabled);
        idToAction.put("3b576869-a4ec-4529-8536-b80a7769e899", enabled);
        idToAction.put("75668c1f-73b5-4cf0-bb93-3ecf5cb7cc84", enabled);
        idToAction.put("26190899-1602-49e8-8b27-eb1d0a1ce869", enabled);
        idToAction.put("e6db77e5-3df2-4cf1-b95a-636979351e5b", enabled);
        idToAction.put("b2b3f03d-6a65-4f7b-a9c7-1c7ef74a9ba4", enabled);
        idToAction.put("92e97fa1-2edf-4476-bdd6-9dd0b4dddc7b", enabled);
        idToAction.put("c1db55ab-c21a-4637-bb3f-a12568109d35", enabled);
        for (Map.Entry<String, String> entry : idToAction.entrySet()) {
            System.out.println("Add-MpPreference -AttackSurfaceReductionRules_Ids " + entry.getKey() + " -AttackSurfaceReductionRules_Actions " + entry.getValue());
        }
    }

    private static void compare() throws IOException {
        Map<String, String> idToName = AsrRuleFetcher.fetchAllRules();
        Map<String, Action> idToAction = AsrRuleGetter.getRules();
        for (Map.Entry<String, String> entry : idToName.entrySet()) {
            String ruleId = entry.getKey();
            String ruleName = entry.getValue();
            Action action = idToAction.get(ruleId);
            log.info("ID: " + ruleId + " , Action: " + action + " , Name : " + ruleName);
        }
    }
}
