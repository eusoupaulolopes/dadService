
# coding: utf-8

# In[1]:


import pandas as pd
import unicodedata
import datetime


# In[2]:


url_componentes = 'componentes-graduacao.csv'
url_unidades = 'http://dados.ufrn.br/dataset/da6451a5-1a59-4630-bdc2-97f6be4a59c2/resource/3f2e4e32-ef1a-4396-8037-cbc22a89d97f/download/unidades.csv'
url_docentes = 'http://dados.ufrn.br/dataset/8bf1a468-48ff-4f4d-95ee-b17b7a3a5592/resource/ff0a457e-76fa-4aca-ad99-48aebd7db070/download/docentes.csv'
url_avaliacoes = 'http://dados.ufrn.br/dataset/d5723d75-7e6e-4264-82aa-b96909b69f63/resource/7accd1d2-2793-460e-b98d-87a0679b9155/download/avaliacaodocencia.csv'


# In[3]:


df_unidades = pd.read_csv(url_unidades, ';')
df_unidades = df_unidades[['id_unidade_responsavel', 'unidade_responsavel']]
df_unidades.rename(columns={'id_unidade_responsavel': 'id_unidade', 'unidade_responsavel': 'lotacao'}, inplace=True)
df_unidades = df_unidades[(df_unidades['lotacao'] == 'DEPARTAMENTO DE INFORMATICA E MATEMATICA APLICADA') | (df_unidades['lotacao'] == 'INSTITUTO METROPOLE DIGITAL')].drop_duplicates()
df_unidades.reset_index(inplace=True, drop=True)
df_unidades.head()


# In[4]:


df_docentes = pd.read_csv(url_docentes, ';')
df_docentes.columns


# In[5]:


df_docentes = df_docentes[df_docentes['categoria'] == 'PROFESSOR DO MAGISTERIO SUPERIOR']


# In[6]:


df_docentes = df_docentes[['id_servidor', 'nome', 'formacao', 'id_unidade_lotacao', 'admissao']]
df_docentes.rename(columns={'id_servidor': 'id_docente', 'admissao': 'data_admissao', 'id_unidade_lotacao': 'id_unidade'}, inplace=True)
df_docentes = df_docentes.merge(df_unidades[['id_unidade']], on='id_unidade', how='inner')
df_docentes['data_admissao'] = [ data.date() for data in pd.to_datetime(df_docentes['data_admissao'])]
df_docentes['id_unidade'].value_counts()


# In[7]:


df_componentes = pd.read_csv(url_componentes, sep=',')
df_componentes = df_componentes[(df_componentes['unidade_responsavel'] == 'DEPARTAMENTO DE INFORMÁTICA E MATEMÁTICA APLICADA') | (df_componentes['unidade_responsavel'] == 'INSTITUTO METROPOLE DIGITAL')]
df_componentes.head()


# In[8]:


unidades_normalizadas = [unicodedata.normalize('NFKD', unicode(nome, "utf-8")).encode('ASCII', 'ignore') for nome in df_componentes['unidade_responsavel']]
series = pd.Series(data=unidades_normalizadas, index=df_componentes.index)
df_componentes['unidade_responsavel'] = series


# In[9]:


df_componentes = df_componentes.merge(df_unidades[['id_unidade', 'lotacao']], left_on='unidade_responsavel', right_on='lotacao', how='inner')


# In[10]:


df_componente_curricular = df_componentes[['id_componente', 'codigo']]
df_componente_curricular.head()


# In[11]:


df_componente_lotacao = df_componentes[['codigo', 'id_unidade', 'nome']]
df_componente_lotacao.head()


# In[12]:


turma_periodo = ['http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/5e77d066-d506-45eb-a21e-76aa79616fef/download/turmas-2017.1',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/01fe7343-fdf0-4a67-b915-2386b7c2fecb/download/turmas-2017.2',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/322d9977-ba15-47f1-8216-75a1ca78e197/download/turmas-2016.1.csv',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/5e8e3228-7f22-40a2-9efd-561c44844567/download/turmas-2016.2.csv',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/4d5aee5a-00b0-4ed6-a4be-59fa77a56797/download/turmas-2015.1.csv',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/7c59621c-4a8b-49d4-b319-83cfea9bdf28/download/turmas-2015.2.csv',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/e6e4144f-4042-4fdc-84e0-76e9ec27ae7c/download/turmas-2014.1.csv',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/2c69547b-920f-4ec2-92c0-3fbc19512165/download/turmas-2014.2.csv',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/0d96d930-8058-4def-9044-c3ae04c1f40c/download/turmas-2013.1.csv',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/e7e3cf12-a29b-491a-a895-021a43819197/download/turmas-2013.2.csv',]


# In[13]:


frames = [pd.read_csv(periodo, sep=';', usecols=['id_turma', 'ano', 'nivel_ensino', 'periodo', 'id_componente_curricular']) for periodo in turma_periodo]


# In[14]:


df_turmas = pd.concat(frames)


# In[15]:


df_turmas.rename(columns={'nivel_ensino': 'nivel', 'id_componente_curricular': 'id_componente'}, inplace=True)
df_turmas = df_turmas[df_turmas['nivel'] == 'GRADUAÇÃO']
df_turmas['periodo'] = df_turmas['periodo'].astype(int) 
df_turmas['id_componente'] = df_turmas['id_componente'].astype(int)
df_turmas = df_turmas.merge(df_componente_curricular[['id_componente']], on='id_componente', how='inner')
df_turmas.head()


# In[16]:


df_avaliacoes = pd.read_csv(url_avaliacoes, sep=';', usecols=['id_docente', 'id_turma', 'qtd_discentes', 'postura_profissional_media', 'postura_profissional_DP', 'atuacao_profissional_media', 'atuacao_profissional_DP'])
df_avaliacoes = df_avaliacoes.merge(df_turmas[['id_turma']].astype(int), on='id_turma', how='inner')
df_avaliacoes = df_avaliacoes.merge(df_docentes[['id_docente']].astype(int), on='id_docente', how='inner')
df_avaliacoes.rename(columns={'postura_profissional_media': 'postura_profissional', 'atuacao_profissional_media': 'atuacao_profissional'}, inplace=True)
df_avaliacoes.head()


# In[17]:


df_avaliacoes.to_csv('avaliacao.csv', sep=';', index=False)
df_componente_lotacao.to_csv('componente_lotacao.csv', sep=';', index=False)
df_componente_curricular.to_csv('componente_curricular.csv', sep=';', index=False)
df_docentes.to_csv('docente.csv', sep=';', index=False)
df_unidades.to_csv('unidade.csv', sep=';', index=False)
df_turmas.to_csv('turma.csv', sep=';', index=False)

