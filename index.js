import React,{ useEffect, useState } from 'react';
import {AppRegistry, StyleSheet, FlatList, Text, View, Image, NativeEventEmitter, NativeModules, TouchableOpacity} from 'react-native';
import { ActivityIndicator } from 'react-native-paper';

const CharacterDetailApp = () => {
  const eventEmitter = new NativeEventEmitter();
  const [isFavorite, setIsFavorite] = useState(false);

  const [character, setCharacter] = useState({});
  const [loading, setLoading] = useState(true);

  const sendMessageToNative = () => {
    var action = "save";
    if(isFavorite) action = "remove";
    NativeModules.CommunicationModule.sendMessageToAndroid(JSON.stringify(character), action, (response) => {
      console.log('Response from Android :: ', response);
    });
  };

  useEffect(() => {
    const eventListener = eventEmitter.addListener(
      'CHARACTER_ID',
      event => {

        fetch("https://rickandmortyapi.com/api/character/" + event.characterId)
          .then((resp) => resp.json())
          .then((json) => setCharacter(json))
          .catch((error) => console.error(error))
          .finally(() => setLoading(false));

          console.log(event.characterId);
          setIsFavorite(event.characterStatus);
      },
    );

    const eventListener1 = eventEmitter.addListener(
      'CHARACTER_STATUS',
      event => {
          console.log(event.characterStatus);
          setIsFavorite(event.characterStatus);
      },
    );

    return () => {
      eventEmitter.removeAllListeners();
      eventListener.remove();
      eventListener1.remove();
    };
  }, []);

  const toggleFavorite = () => {
    sendMessageToNative();
    setIsFavorite(!isFavorite);
  };

  if (loading) {
    return <ActivityIndicator size="large" color="#000000" style={styles.loader} />;
  }

  return (
    <View style={styles.container}>
      <Image source={{ uri: character.image }} style={styles.image} />
      <Text style={styles.name}>{character.name}</Text>
      <Text style={styles.details}>{character.status} - {character.species}</Text>
      <Text style={styles.details}>Gender: {character.gender}</Text>
      <Text style={styles.details}>Origin: {character.origin.name}</Text>
      <Text style={styles.details}>Location: {character.location.name}</Text>

      <TouchableOpacity style={[styles.button, isFavorite ? styles.buttonFavorite : {}]} onPress={toggleFavorite}>
        <Text style={styles.buttonText}>{isFavorite ? 'Remove from Favorite' : 'Add to Favorite'}</Text>
      </TouchableOpacity>

      <Text style={styles.sectionTitle}>Episodes</Text>
      <FlatList
        data={character.episode}
        keyExtractor={(item) => item}
        renderItem={({ item }) => (
          <Text style={styles.episode}>{item}</Text>
        )}
        contentContainerStyle={styles.episodeList}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#1e1e1e',
    alignItems: 'center',
    padding: 20,
  },
  image: {
    width: 200,
    height: 200,
    borderRadius: 100,
    marginBottom: 10,
  },
  name: {
    fontSize: 28,
    fontWeight: 'bold',
    color: '#fff',
  },
  details: {
    fontSize: 16,
    color: '#aaa',
    marginTop: 5,
  },
  sectionTitle: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#00ff00',
    marginTop: 20,
    alignSelf: 'flex-start',
  },
  episodeList: {
    marginTop: 10,
  },
  episode: {
    fontSize: 16,
    color: '#fff',
    paddingVertical: 5,
  },
  button: {
    marginTop: 15,
    paddingVertical: 10,
    paddingHorizontal: 20,
    backgroundColor: '#00ff00',
    borderRadius: 8,
  },
  buttonFavorite: {
    backgroundColor: '#ff4444',
  },
  buttonText: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#000',
  },
  loader: {
    flex: 1,
    justifyContent: 'center',
  },
});

AppRegistry.registerComponent(
  'CharacterDetailApp',
  () => CharacterDetailApp,
);
