<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Default Suite">
    <test name="home">
        <classes>
            <class name="Tests.Test_Authorization">
                <methods>
                    <include name="LogInWithNegativeCasesPass"/>
                </methods>
            </class> <!-- Tests.Test_Authorization -->
        </classes>
    </test> <!-- home -->
</suite> <!-- Default Suite -->
