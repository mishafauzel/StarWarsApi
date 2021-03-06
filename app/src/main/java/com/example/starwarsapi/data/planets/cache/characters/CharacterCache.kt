package com.example.starwarsapi.data.planets.cache.characters

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.example.starwarsapi.core.HasExtraData
import com.example.starwarsapi.core.IsDataFull
import com.example.starwarsapi.data.planets.cache.planets.PlanetCache
import com.example.starwarsapi.domain.planets.CharacterDomain
import com.example.starwarsapi.presentation.character.base_data.CharacterFullUI
import com.example.starwarsapi.presentation.character.items.CharacterFullInfoItem

interface CharacterCache : IsDataFull, HasExtraData {
    fun <T> map(mapper: Mapper<T>): T

    @Entity(
        tableName = "character_table", foreignKeys = [ForeignKey(
            entity = PlanetCache.Base::class,
            parentColumns = ["id"], childColumns = ["planet_id"], onDelete = CASCADE
        )]
    )
    data class Base(
        @PrimaryKey(autoGenerate = false)
        val id: Int,
        @ColumnInfo(name = "planet_id")
        val planetId: Int,
        @ColumnInfo(name = "character_name")
        val characterName: String = "",
        @ColumnInfo(name = "birth_year")
        val birthYear: String = "",//это чтобы ItemViews хоть чем то отличались
        @ColumnInfo(name = "hair_color")
        val hairColor: String = "",
        @ColumnInfo(name = "skin_color")
        val skinColo: String = "",
        @ColumnInfo(name = "gender")
        val gender: String = "",
        @ColumnInfo(name = "mass")
        val mass: String = "",
        @ColumnInfo(name = "height")
        val height: String = "",
    ) : CharacterCache {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(
            id,
            planetId,
            characterName,
            birthYear,
            hairColor,
            skinColo,
            gender,
            mass,
            height
        )

        override fun isFull() = characterName.isNotEmpty()

        override fun hasExtraData() = mass.isNotEmpty()

    }

    interface Mapper<T> {

        fun map(
            id: Int,
            planetId: Int,
            characterName: String,
            birthYear: String,
            hairColor: String = "",
            skinColor: String = "",
            gender: String = "",
            mass: String = "",
            height: String = ""
        ): T

        class BaseToListOfCharacterDomain : Mapper<CharacterDomain> {

            override fun map(
                id: Int,
                planetId: Int,
                characterName: String,
                birthYear: String,
                hairColor: String,
                skinColor: String,
                gender: String,
                mass: String,
                height: String
            ) = CharacterDomain.Base(id, characterName, planetId, birthYear)


        }

        class CharacterToIdMapper() : Mapper<String> {

            override fun map(
                id: Int,
                planetId: Int,
                characterName: String,
                birthYear: String,
                hairColor: String,
                skinColor: String,
                gender: String,
                mass: String,
                height: String
            ) = id.toString()

        }

        class CharacterToCharacterFullUI() : Mapper<CharacterFullUI> {

            override fun map(
                id: Int,
                planetId: Int,
                characterName: String,
                birthYear: String,
                hairColor: String,
                skinColor: String,
                gender: String,
                mass: String,
                height: String
            ) = CharacterFullUI.Base(
                characterFullInfoItem = listOf(
                    CharacterFullInfoItem(
                        characterName,
                        birthYear,
                        id,
                        hairColor,
                        skinColor,
                        gender,
                        mass,
                        height
                    )
                )
            )

        }
    }
}