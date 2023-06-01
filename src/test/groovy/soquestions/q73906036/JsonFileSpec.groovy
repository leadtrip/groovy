package soquestions.q73906036

import mikew.soquestions.q73906036.JsonFile
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Paths

class JsonFileSpec extends Specification{

    def "Json file is returned"(){
        given:
            JsonFile jsonFile = new JsonFile(['one', 'two'])
        when:
            jsonFile.createTheFile()
            jsonFile.writingTheFile()
        then:
            Files.exists(Paths.get("items.json"))
    }
}
