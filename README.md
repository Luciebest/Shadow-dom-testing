# 13.javascript-executor-shadow-dom
Selenium JavascriptExecutor, ShadowDom, ShadowDom with js, ShadowDom with Selenium getShadowRoot()

### 1.0  The PracticeShadowDom.feature and  ShadowDomSteps are of interest. Also FrameworkConfiguration
### 1.1  Review the javascriptExecutor @Bean declaration in FrameworkConfiguration
```bash
  @Bean(name = "javascriptExecutor")
  JavascriptExecutor getJavascriptExecutor(WebDriver getDriver) {
    return ((JavascriptExecutor) getDriver);
  }
```
### 1.2  Review the javascriptExecutor autowire using @Qualifier  in ShadowDomSteps

```bash
    @Autowired
    @Qualifier("driver")
    private WebDriver driver;

    @Autowired
    @Qualifier("javascriptExecutor")
    private JavascriptExecutor javascriptExecutor;

```
https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-autowired-annotation-qualifiers
https://www.baeldung.com/spring-qualifier-annotation

### 1.3 Review ShadowDomSteps for

http://watir.com/examples/shadow_dom.html

### 1.3.1 JavascriptExecutor approach to reach elements inside shadow dom
```bash
WebElement shadowHost = driver.findElement(By.id("shadow_host"));
SearchContext searchContext = (SearchContext) javascriptExecutor.executeScript("return arguments[0].shadowRoot", shadowHost);
```

Then the searchContext is used with findElement or findElements

### 1.3.2 Selenoium approach to reach elements inside shadow dom using getShadowRoot()
```bash
        WebElement shadowHost1 = driver.findElement(By.id("shadow_host"));
        SearchContext searchContext1 = shadowHost1.getShadowRoot();
```

Same approach is used if we have nested elements.

## 2. (Assignment) For the same shadow dom page http://watir.com/examples/shadow_dom.html enter text in input text and select the checkbox.  Verify the text was entered and checkbox was selected.

## 3. (Assignment) optional. Try to come up with a general method for interacting with an elment that is inside a nested shadow root structure.
```bash
        public SearchContext getElementSearchContext (List<By> shadowHostList, By elementLocator   ) {
        }
```

You can call multiple private methods in this method, but only this one can be public and be used for example like this
