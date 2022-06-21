package com.example.starwarsapi.data.planets.cloud.characters

class TestCharacterCloudDataSource(private val dataSource: MutableMap<String, CharacterCloud>) :
    CharacterCloudDataSource {

    override suspend fun getCharactersById(listOfIds: List<String>): CharactersCloud {
        val listOfCharacter: MutableList<CharacterCloud> = mutableListOf()
        listOfIds.forEach { id ->
            dataSource[id]?.let { characterCloud ->
                listOfCharacter.add(characterCloud)
            }
        }
        return CharactersCloud.Base(listOfCharacter)
    }

}