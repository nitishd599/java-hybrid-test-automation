<h1 align="center">🚀 Java Hybrid Test Automation Framework</h1>

<p align="center">
A scalable, parallel-safe automation framework supporting <b>Web, Mobile & API testing</b><br/>
Built using <b>Selenium, Appium, Cucumber, TestNG & Rest Assured</b>
</p>

<hr/>

<h2>📌 Framework Highlights</h2>
<ul>
  <li>Parallel execution using <b>ThreadLocal</b></li>
  <li>Cloud & local execution support</li>
  <li>Zero business logic inside step definitions</li>
  <li>Config-driven execution (local / CI)</li>
  <li>Beginner-friendly & enterprise-ready</li>
</ul>

<hr/>

<h2>📂 Project Structure Overview</h2>

<h3>🔹 core.api.auth</h3>
<table>
  <tr><th>Class</th><th>Description</th></tr>
  <tr>
    <td><b>AuthManager</b></td>
    <td>
      Handles authentication token lifecycle<br/>
      <b>Pattern:</b> Singleton<br/>
      ✔ Token generated once<br/>
      ✔ Auto-refresh supported<br/>
      ✔ No duplication across tests
    </td>
  </tr>
</table>

<h3>🔹 core.api.builder</h3>
<table>
  <tr><th>Class</th><th>Description</th></tr>
  <tr>
    <td><b>RequestBuilder</b></td>
    <td>
      Builds Rest Assured requests<br/>
      <b>Pattern:</b> Builder<br/>
      ✔ Base request<br/>
      ✔ Auth request<br/>
      ✔ Config-driven logging<br/>
      ✔ Stored in ScenarioContext
    </td>
  </tr>
</table>

<h3>🔹 core.api.utils</h3>
<table>
  <tr><th>Class</th><th>Description</th></tr>
  <tr>
    <td><b>ApiUtils</b></td>
    <td>
      Executes API calls (GET / POST / PUT)<br/>
      Keeps execution logic out of step definitions
    </td>
  </tr>
  <tr>
    <td><b>PlaceholderResolver</b></td>
    <td>
      Dynamically replaces <code>{{id}}</code> using ScenarioContext
    </td>
  </tr>
</table>

<h3>🔹 core.api.payload</h3>
<table>
  <tr><th>Class</th><th>Description</th></tr>
  <tr>
    <td><b>PayloadBuilder</b></td>
    <td>Converts POJO / Map → JSON</td>
  </tr>
  <tr>
    <td><b>PayloadLoader</b></td>
    <td>Loads JSON payload files from resources</td>
  </tr>
</table>

<hr/>

<h3>🔹 core.driver</h3>
<p><b>Responsible for Web & Mobile driver lifecycle management</b></p>

<table>
  <tr>
    <th>Class</th>
    <th>Purpose</th>
    <th>Pattern</th>
  </tr>
  <tr>
    <td>DriverManager</td>
    <td>Stores driver instances</td>
    <td>ThreadLocal</td>
  </tr>
  <tr>
    <td>WebDriverFactory</td>
    <td>Creates web drivers</td>
    <td>Factory</td>
  </tr>
  <tr>
    <td>MobileDriverFactory</td>
    <td>Creates mobile drivers</td>
    <td>Factory</td>
  </tr>
  <tr>
    <td>CloudCapabilityProvider</td>
    <td>Cloud capability contract</td>
    <td>Interface</td>
  </tr>
  <tr>
    <td>BrowserStackCaps</td>
    <td>BrowserStack configuration</td>
    <td>Strategy</td>
  </tr>
  <tr>
    <td>LambdaTestCaps</td>
    <td>LambdaTest configuration</td>
    <td>Strategy</td>
  </tr>
</table>

<ul>
  <li>✔ Parallel safe execution</li>
  <li>✔ Cloud & local support</li>
  <li>✔ Clean driver teardown</li>
</ul>

<hr/>

<h3>🔹 core.actions</h3>
<p>Wrapper around Selenium / Appium interactions</p>

<table>
  <tr><th>Class</th><th>Purpose</th></tr>
  <tr>
    <td>ElementActions</td>
    <td>Web element interactions</td>
  </tr>
  <tr>
    <td>MobileActions</td>
    <td>Mobile gestures & actions</td>
  </tr>
</table>

<ul>
  <li>Centralized waits</li>
  <li>Improved error handling</li>
  <li>Reduced flaky tests</li>
</ul>

<hr/>

<h3>🔹 core.logging</h3>
<table>
  <tr><th>Class</th><th>Description</th></tr>
  <tr>
    <td>FrameworkLogger</td>
    <td>Wrapper over Log4j2</td>
  </tr>
  <tr>
    <td>LogContext</td>
    <td>Thread-level logging support</td>
  </tr>
</table>

<ul>
  <li>✔ Consistent logs</li>
  <li>✔ CI-friendly output</li>
</ul>

<hr/>

<h3>🔹 core.reports</h3>
<table>
  <tr><th>Class</th><th>Description</th></tr>
  <tr>
    <td>ExtentManager</td>
    <td>Report lifecycle management</td>
  </tr>
  <tr>
    <td>ExtentTestManager</td>
    <td>Thread-safe test nodes</td>
  </tr>
</table>

<hr/>

<h2>⚙️ Execution Options</h2>

<h4>✔ Run from IDE</h4>
<ul>
  <li>Right-click <b>TestRunner</b> → Run</li>
  <li>Uses default feature path & tags</li>
</ul>

<h4>✔ Run using Maven</h4>
<pre>
mvn clean test -Dcucumber.filter.tags=@smoke
</pre>

<h4>✔ Run on Jenkins</h4>
<ul>
  <li>Feature path & tags configurable via Jenkins parameters</li>
  <li>Same configuration works locally & on CI</li>
</ul>

<hr/>


<h2>🏗️ Framework Architecture</h2>

<pre>
                    ┌──────────────────────────┐
                    │        TestNG Suite       │
                    │      (testng.xml)         │
                    └─────────────┬────────────┘
                                  │
                                  ▼
                    ┌──────────────────────────┐
                    │     Cucumber Runner       │
                    │   (TestRunner.java)       │
                    └─────────────┬────────────┘
                                  │
        ┌─────────────────────────┼─────────────────────────┐
        │                         │                         │
        ▼                         ▼                         ▼

┌──────────────────┐   ┌──────────────────┐   ┌──────────────────┐
│   Web Scenarios  │   │  Mobile Scenarios │   │   API Scenarios  │
│   (Features)     │   │   (Features)      │   │   (Features)     │
└────────┬─────────┘   └────────┬─────────┘   └────────┬─────────┘
         │                      │                      │
         ▼                      ▼                      ▼

┌──────────────────────────────────────────────────────────────┐
│                     Step Definitions                          │
│        (No execution logic – orchestration only)              │
└────────┬─────────────────────┬──────────────────────┬────────┘
         │                     │                      │
         ▼                     ▼                      ▼

┌───────────────┐   ┌──────────────────┐   ┌──────────────────┐
│ core.actions  │   │  core.driver     │   │   core.api       │
│ ElementActions│   │ DriverManager    │   │ RequestBuilder   │
│ MobileActions │   │ DriverFactories  │   │ ApiUtils         │
└───────┬───────┘   └────────┬─────────┘   └────────┬─────────┘
        │                    │                      │
        ▼                    ▼                      ▼

┌──────────────────────────────────────────────────────────────┐
│                    Shared Core Layers                         │
│  - ScenarioContext (ThreadLocal)                              │
│  - ConfigManager (Singleton)                                  │
│  - FrameworkLogger (Thread-aware logging)                     │
│  - Extent Reporting                                           │
└──────────────────────────────────────────────────────────────┘
</pre>

<p>
<b>Key Design Principles:</b><br/>
✔ No business logic in step definitions<br/>
✔ Thread-safe execution using ThreadLocal<br/>
✔ Clear separation between orchestration and execution layers
</p>


<h2>🎯 Why This Framework?</h2>
<ul>
  <li>Designed for <b>scalability & maintainability</b></li>
  <li>Easy for junior engineers to contribute</li>
  <li>Built using real-world enterprise patterns</li>
  <li>Ideal for Web, Mobile & API automation</li>
</ul>

<hr/>

<p align="center">
<b>⭐ If this framework helped you, consider starring the repo ⭐</b>
</p>
