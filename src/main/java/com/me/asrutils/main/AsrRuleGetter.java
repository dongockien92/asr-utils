package com.me.asrutils.main;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.me.asrutils.model.Action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Get all rules from system
 */
public final class AsrRuleGetter {
    public static Map<String, Action> getRules() throws IOException {
        List<String> ruleIds = getRuleIds();
        List<Action> ruleActions = getRuleActions();
        if (ruleIds.size() != ruleActions.size()) {
            throw new IllegalArgumentException("Ids and actions are mismatch");
        }
        Map<String, Action> idToAction = new LinkedHashMap<>();
        for (int i = 0; i < ruleIds.size(); i++) {
            idToAction.put(ruleIds.get(i), ruleActions.get(i));
        }
        return idToAction;
    }

    private static List<String> getRuleIds() throws IOException {
        String command = "powershell.exe Get-MpPreference | Select-Object AttackSurfaceReductionRules_Ids | ConvertTo-Json";
        String powershellOutput = execute(command);
        JsonArray arrIds = JsonParser.parseString(powershellOutput).getAsJsonObject().get("AttackSurfaceReductionRules_Ids").getAsJsonArray();
        List<String> results = new ArrayList<>();
        for (int i = 0; i < arrIds.size(); i++) {
            results.add(arrIds.get(i).getAsString());
        }
        return results;
    }

    private static List<Action> getRuleActions() throws IOException {
        String command = "powershell.exe Get-MpPreference | Select-Object AttackSurfaceReductionRules_Actions | ConvertTo-Json";
        String powershellOutput = execute(command);
        JsonArray arrIds = JsonParser.parseString(powershellOutput).getAsJsonObject().get("AttackSurfaceReductionRules_Actions").getAsJsonArray();
        List<Action> results = new ArrayList<>();
        for (int i = 0; i < arrIds.size(); i++) {
            int mode = arrIds.get(i).getAsInt();
            results.add(Action.parse(mode));
        }
        return results;
    }

    private static String execute(String powershellCommand) throws IOException {
        Process powerShellProcess = Runtime.getRuntime().exec(powershellCommand);
        powerShellProcess.getOutputStream().close();

        StringBuilder sb = new StringBuilder();
        String line;
        System.out.println("Standard Output:");
        BufferedReader stdout = new BufferedReader(new InputStreamReader(
                powerShellProcess.getInputStream()));
        while ((line = stdout.readLine()) != null) {
            sb.append(line);
        }
        stdout.close();

        System.out.println("Standard Error:");
        BufferedReader stderr = new BufferedReader(new InputStreamReader(
                powerShellProcess.getErrorStream()));
        while ((line = stderr.readLine()) != null) {
            System.out.println(line);
        }
        stderr.close();

        System.out.println("Done");
        return sb.toString();
    }
}
