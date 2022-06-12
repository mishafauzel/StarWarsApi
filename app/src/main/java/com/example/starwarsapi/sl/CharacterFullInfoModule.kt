package com.example.starwarsapi.sl

import com.example.starwarsapi.data.character.CharacterFullIInfoRepository
import com.example.starwarsapi.data.character.cache.CharacterCacheDataSource
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloud
import com.example.starwarsapi.data.planets.UrlIdMapper
import com.example.starwarsapi.data.planets.cache.AbstractDatabase
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cloud.ProvideServices
import com.example.starwarsapi.domain.characters.CharacterInteractor
import com.example.starwarsapi.presentation.DataKeeper
import com.example.starwarsapi.presentation.character.CharacterFullComunication
import com.example.starwarsapi.presentation.character.CharacterFullInfoErrorCommunication
import com.example.starwarsapi.presentation.character.CharacterFullViewModel
import com.example.starwarsapi.presentation.character.CharactersErrorHandle
import com.example.starwarsapi.presentation.planets.PlanetsViewModel
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module

class CharacterFullInfoModule(private val coreModule: CoreModule,private val dataModule: DataModule) :
    Module<CharacterFullViewModel> {
    override fun viewModel(): CharacterFullViewModel {
        val charDao =
            coreModule.provideRoomDatabase(AbstractDatabase::class.java).provideCharactersDao()
        val provideServices = ProvideServices.Base(coreModule)
        val urlIdMapper=UrlIdMapper.IdConverter()
        val characterFullInfoRrepositor =
            CharacterFullIInfoRepository.Base(CharacterCacheDataSource.Base(charDao),
            CharacterCache.Mapper.CharacterToCharacterFullUI(),provideServices.provideFullInfoCharacter(),
            CharacterFullInfoCloud.Mapper.BaseToCharacterCache(urlIdMapper))
        val errorCommunication=CharacterFullInfoErrorCommunication.Base()
        val charactersErrorHandle=CharactersErrorHandle(errorCommunication)
        val characterInteractor=CharacterInteractor.Base(characterFullInfoRrepositor,charactersErrorHandle,coreModule.dispatchers())
        return CharacterFullViewModel(coreModule.provideCanGoBack(),characterInteractor,coreModule.provideProgressCommunication(),
        errorCommunication,CharacterFullComunication.Base(),coreModule.dispatchers(),dataModule.getDataKeeper())
    }
}