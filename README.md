#Issues

* Issue 1 - LinearLayout had width written as weidth in activity_main.xml
* Issue 2 - string was written as plural (strings) in activity_main.xml
* Issue 3 - setInstance wasn't set to static in Loan.java
* Issue 4 - Multiple getters and setters in Loan.java
* Issue 5 - All EditText's were missing (in MainActivity.java) the reference to the EditText in xml file (activity_main.xml)
* Issue 6 - Issue 6 - 2 buttons (in MainActivity.java) were referenced after they were needed in xml file (activity_main.xml)
* Issue 7 - "disable" method wasn't created in MainActivity.java but was used in onCreate method
* Issue 8 - setText was marked as "setTextTo" in MainActivity.java. Needs to be "setText".
* Issue 9 - "year" and "term" were declared as strings in MainActivity.java
* Issue 10 - all Toast messages (5) were written falsely in MainActivity.java
* Issue 11 - 1 case where getInstance() was written with a typo in MainActivity.java
* Issue 12 - "year" and "term" were falsely converted into int in MainActivity.java
* Issue 13 - GridView was outside of LinearLayout borders in activity_plan.xml
* Issue 14 - "R.id.foo" was referenced although "R.string.foo" was needed in PlanActivity.java
* Issue 15 - String for illegal term value was missing in strings.xml
* Issue 16 - Validation of "year" was incorrect in MainActivity.java. Fixed limits and "or" was needed.
* Issue 17 - intent was missing "mainActivity.this" in MainActivity.java
* Issue 18 - EditText and Button were private. Changed it to public in MainActivity.java
* Issue 19 - adapter was falsely set in PlanActivity.java
* Issue 20 - TableLayout and LinearLayout were matching parent. So the "payment" part didn't fit the screen in activity_main.xml
* Issue 21 - "term" was including 0, but it should be 1 to 12 in MainActivity.java
* Issue 22 - "rate" limits were set falsely. Fixed those in MainActivity.java
* Issue 23 - Was missing activity "PlanActivity.java" in AndroidManifest.xml
