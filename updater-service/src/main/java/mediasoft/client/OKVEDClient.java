package mediasoft.client;

import mediasoft.dto.okved.OkvedSuggestion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "dadata", url = "https://suggestions.dadata.ru", configuration = FeignConfig.class)
public interface OKVEDClient {
    @GetMapping("/suggestions/api/4_1/rs/suggest/okved2")
    OkvedSuggestion findById(@RequestParam("query") String OKVED);
}
