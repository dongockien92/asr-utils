package com.me.asrutils.main;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Fetch all rules from MS Site
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AsrRuleFetcher {
    public static Map<String, String> fetchAllRules() throws IOException {
        final String url = "https://docs.microsoft.com/en-us/microsoft-365/security/defender-endpoint/attack-surface-reduction-rules-reference?view=o365-worldwide";
        final Document doc = Jsoup.connect(url).get();
        Element table = doc.select("table:has(table>thead>tr>th:contains(Rule GUID))").get(0);
        Elements rows = table.select("tbody").get(0).select("tr");
        Map<String, String> guidToName = new LinkedHashMap<>();
        for (int i = 0; i < rows.size(); i++) { //first row is the col names so skip it.
            Element row = rows.get(i);
            Elements cols = row.select("td");
            String guid = cols.get(1).text();
            String name = cols.get(0).text();
            guidToName.put(guid, name);
        }
        return guidToName;
    }
}
