import React from 'react';
import {View, Text, StyleSheet, FlatList } from 'react-native';
import { List, Divider, Button } from 'react-native-paper';
import { StatusBar } from 'expo-status-bar';
import Header from '../components/Header';
import { useNavigation } from '@react-navigation/core';


const DATA = [
    {
        ID: 1,
        loja: 'Lótus',
        produto: 'Mouse Wireless',
    },
    {
        ID: 2,
        loja: 'Bela Boutique',
        produto: 'Multivitamínico',
    },
    {
        ID:3,
        loja: 'Chique Demais',
        produto: 'Sabonete líquido'
    }
];

const Item = ({title}) => (
    <View style={styles.item}>
      <Text style={styles.title}>{title}</Text>
    </View>
  );

export default function History() {
    const navigation = useNavigation();

    const renderItem = ({item}) => (<><List.Item title={item.loja} 
    right={() => <List.Item title ={item.produto}/> }
     />
     <Divider theme={{ colors: { primary: 'green' } }} /></>)
  return (

    <View style={styles.container}>
        <Header title = "Histórico de Resgate"/>
        <List.Item 
          titleStyle={{fontWeight: 'bold'}} 
          style={styles.listFirst} 
          title='Loja' 
          right={() => 
            <List.Item 
              titleStyle={{fontWeight: 'bold'}} 
              title= 'Produto'
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
          icon="cart-outline" 
          style={{width: 20}} 
          buttonColor='#0025bf' 
          mode="contained" 
          onPress={() => navigation.navigate('Rescue')}>
        </Button>
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
        marginTop: 50 }
  });
