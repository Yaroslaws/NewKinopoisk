# language: ru
@withdrawal
Функция: Заходм на сайт кинопоиск

  @success
  Сценарий: Заходим на сайт
    Дано пользователь открывает сайт "https://www.kinopoisk.ru/"
    Тогда логинимся на кинопоиске
    Тогда проверяем пользователя
    Тогда выход из аккаунта



  @fail
  Сценарий: Тест не прошел