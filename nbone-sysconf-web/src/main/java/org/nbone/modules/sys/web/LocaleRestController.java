package org.nbone.modules.sys.web;

import io.swagger.annotations.*;
import org.nbone.framework.spring.web.bind.annotation.ResultResponseBody;
import org.nbone.framework.spring.web.util.CacheControlUtils;
import org.nbone.lang.Dict;
import org.nbone.modules.sys.example.CountryExample;
import org.nbone.modules.sys.example.LanguageExample;
import org.nbone.util.LocaleUtils;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 国家语言列表
 *
 * @author thinking
 * @version 1.0
 * @since 2019-12-20
 */
@Api(tags = {"国家语言信息列表"})
@RestController
@RequestMapping(value = {"${nbone.sysconf.locale.path:sys/locale}"})
public class LocaleRestController {

    private List<Dict<String>> countries;
    private List<Dict<String>> languages;

    private Map<String, String> countriesMap;
    private Map<String, String> languagesMap;

    /**
     * TimeUnit.HOURS
     */
    private final static long maxAge = 3;

    @ApiOperation(value = "查询全部国家列表", notes = "传统URL风格,使用Form/Query参数方式请求")
    //@RequestMapping(value = "countries", method = {RequestMethod.GET})
    @ResultResponseBody
    public List<Dict<String>> countries(HttpServletRequest request, HttpServletResponse response) {
        if (countries == null) {
            Map<String, String> map = LocaleUtils.getCountryMap(Locale.CHINA);
            countries = Dict.from(map);
        }
        CacheControlUtils.applyCache(response, CacheControl.maxAge(maxAge, TimeUnit.HOURS).cachePublic());
        return countries;
    }

    @ApiOperation(value = "查询全部语言列表", notes = "传统URL风格,使用Form/Query参数方式请求")
    //@RequestMapping(value = "languages", method = {RequestMethod.GET})
    @ResultResponseBody
    public List<Dict<String>> languages(HttpServletRequest request, HttpServletResponse response) {
        if (languages == null) {
            Map<String, String> map = LocaleUtils.getLanguageMap(Locale.CHINA);
            languages = Dict.from(map);
        }
        CacheControlUtils.applyCache(response, CacheControl.maxAge(maxAge, TimeUnit.HOURS).cachePublic());
        return languages;
    }




    @ApiOperation(value = "查询全部国家列表", notes = "传统URL风格,使用Form/Query参数方式请求", response = CountryExample.class)
    @ApiResponses(
            @ApiResponse(code = 202, message = "success", response = Map.class,
                    examples = @Example(value = @ExampleProperty(value = "{'AE':'阿拉伯联合酋长国','JO':'约旦','SY':'叙利亚'}"))))
    @RequestMapping(value = "/map/countries", method = {RequestMethod.GET})
    @ResultResponseBody
    public Map<String, String> mapCountries(HttpServletRequest request, HttpServletResponse response) {
        if (countriesMap == null) {
            Map<String, String> map = LocaleUtils.getCountryMap(Locale.CHINA);
            countriesMap = map;
        }
        CacheControlUtils.applyCache(response, CacheControl.maxAge(maxAge, TimeUnit.HOURS).cachePublic());
        return countriesMap;
    }

    @ApiOperation(value = "查询全部语言列表", notes = "传统URL风格,使用Form/Query参数方式请求", response = LanguageExample.class)
    @ApiResponse(code = 202, message = "success", response = Map.class,
            examples = @Example(value = @ExampleProperty(value = "{'ar':'阿拉伯文','hr':'克罗地亚文','fr':'法文','es':'西班牙文'}")))
    @RequestMapping(value = "/map/languages", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResultResponseBody
    public Map<String, String> mapLanguages(HttpServletRequest request, HttpServletResponse response) {
        if (languagesMap == null) {
            Map<String, String> map = LocaleUtils.getLanguageMap(Locale.CHINA);
            languagesMap = map;
        }
        CacheControlUtils.applyCache(response, CacheControl.maxAge(maxAge, TimeUnit.HOURS).cachePublic());
        return languagesMap;
    }


}
