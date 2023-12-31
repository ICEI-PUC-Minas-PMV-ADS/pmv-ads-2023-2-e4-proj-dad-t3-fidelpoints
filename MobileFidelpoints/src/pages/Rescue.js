import React from 'react';
import {View, Text, StyleSheet, FlatList } from 'react-native';
import { List, Divider, Button, IconButton } from 'react-native-paper';
import { StatusBar } from 'expo-status-bar';
import Header from '../components/Header';
import { useNavigation } from '@react-navigation/core';
import History from './History';

const DATA = [
    {
        ID: 1,
        loja: 'Edglei Sports LTDA',
        point: 200,
    },
    {
        ID: 2,
        loja: 'Ana Bebidas e CIA',
        point: 400,
    },
    {
        ID:3,
        loja: 'Edivania Cosméticos',
        point: 600
    }
];

const Item = ({title}) => (
    <View style={styles.item}>
      <Text style={styles.title}>{title}</Text>
    </View>
  );

export default function Rescue() {

    const navigation = useNavigation();
    const renderItem = ({item}) => 
      (
        <>
          <List.Item
              left={() => 
                <List.Item 
                title ={item.loja} 
                style={{marginRight: 10}}/> 
              }
              title={item.point} 
              right={() => <IconButton
                icon="cart"
                size={20}
                onPress={() => navigation.navigate('First')}/>
              }
          />
            <Divider theme={{ colors: { primary: 'green' } }} />
        </>
      );

  return (

    <View style={styles.container}>
        <Header title = "Resgate de pontos"/>
        <List.Item
          left={() =><List.Item 
            titleStyle={{fontWeight: 'bold'}}
            title='Loja'
            style= {{  marginRight: 100}}
          />}
          titleStyle={{fontWeight: 'bold'}} 
          style={styles.listFirst} 
          title='Pontos' 
          right={() => 
            <List.Item 
              titleStyle={{fontWeight: 'bold'}} 
              title= 'Trocar'
            />
          }
          
        />
        <Divider style={{height: 3, backgroundColor: "#0025bf"}} />
        <View style={{color: '#000', height:10 }}/>
        <FlatList
        data={DATA}
        renderItem={renderItem}
        keyExtractor={item => item.ID}
      />
        <Button 
          icon="shopping" 
          style={{width: 20}} 
          buttonColor='#0025bf' 
          mode="contained" 
          onPress={() => navigation.navigate('First')}>
        </Button>
        <StatusBar style="auto" />
        <StatusBar style="auto" />
    </View>
    
    );
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff'
    },
    listFirst: {
        fontWeight: 'bold' ,
        marginTop: 50
    }
  });