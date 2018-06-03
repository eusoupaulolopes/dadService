import pandas as pd
import requests
import json

api_url = 'http://localhost:8080/'

def make_post(df, endpoint):
    records = json.loads(df.to_json(orient='records'))
    for record in records:
        request = requests.post(endpoint, data=json.dumps(record), headers={'Content-Type': 'application/json'})
        print request.text

def insert(df_url, endpoint):
    df = pd.read_csv(df_url, sep=';')
    make_post(df, endpoint)

def insert_componentes():
    df_lotacao = pd.read_csv('componente_lotacao.csv', sep=';')
    df_curricular = pd.read_csv('componente_curricular.csv', sep=';')
    df = df_lotacao.merge(df_curricular, on='codigo')
    make_post(df, api_url+'componente')

if __name__ == '__main__':
    insert('unidade.csv', api_url+'unidade')
    insert('docente.csv', api_url+'docente')
    insert_componentes()
    # insert('turma.csv', api_url+'turma')
    insert('avaliacao.csv', api_url+'avaliacao')
