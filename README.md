# Учебный проект "Цифровой учет книг в библиотеке"
(стек применяемых технологий: *Spring, Spring Data JPA, PostgreSQL, Thymeleaf, Hibernate*)

*данный проект является продолжением реализации [предыдущего](https://github.com/DmitryIE/Digital_library_Spring_CRUD) с использованием Hibernate и Spring Data JPA и добавлением нового функционала*

**Задача:**

В местной библиотеке решили перейти на цифровой учет книг. Библиотекари имеют возможность регистрировать читателей, выдавать им книги и освобождать книги (после того, как читатель возвращает книгу обратно в библиотеку), искать книги, отслеживать нарушение срока возврата.

Функционал:
1) Страницы добавления, изменения и удаления читателей.
2) Страницы добавления, изменения и удаления книги.
3) Страница со списком всех людей (люди кликабельные - при клике осуществляется переход на страницу читателя).
4) Страница со списком всех книг (книги кликабельные - при клике осуществляется переход на страницу книги).
5) Страница читателя, на которой показаны значения его полей и список книг, которые он взял. Если человек не взял ни одной книги, вместо списка должен быть текст "Читатель пока не взял ни одной книги".
6) Страница книги, на которой показаны значения полей этой книги и имя человека, который взял эту книгу. Если эта книга не была никем взята, должен быть текст "Эта книга свободна".
7) На странице книги, если книга взята читателем, рядом с его именем должна быть кнопка "Освободить книгу". Эта кнопка нажимается библиотекарем тогда, когда читатель возвращает эту книгу обратно в библиотеку. После нажатия на эту кнопку книга снова становится свободна и пропадает из списка книг человека.
8) На странице книги, если книга свободна, должен быть выпадающий список со всеми людьми и кнопка "Назначить книгу". Эта кнопка нажимается библиотекарем тогда, когда читатель хочет забрать эту книгу домой. После нажатия на эту кнопку, книга должна начать принадлежать выбранному человеку и должна появится в его списке книг.
9) Пагинация для книг. Метод контроллера умеет выдавать не только все книги разом, но и разбивать выдачу на страницы.
10) Сортировка книг по году. Метод контроллера умеет выдавать книги в отсортированном порядке.
11) Страница поиска книг. Вводим в поле на странице часть названия книги, получаем все найденные книги. Также, если книга сейчас находится у кого-то, получаем имя этого человека.
12) Автоматическая проверка на то, что человек просрочил возврат книги (если человек взял книгу более 10 дней назад и до сих пор не вернул ее, эта книга на странице этого человека подсвечивается красным цветом).

## Изображения работы программы:
<u>**1. Страница со списком всех читателей**</u>

![](./screenshots/001.jpg)

<u>**2. Создание нового читателя (применяется валидация полей)**</u>

![](./screenshots/002.jpg)

<u>**3. Страница читателя (у читателя не взята ни одна книга)**</u>

![](./screenshots/003.jpg)

<u>**4. Страница редактирования читателя**</u>

![](./screenshots/004.jpg)

<u>**5. Страница со списком всех книг**</u>

![](./screenshots/005.jpg)

<u>**6. Страница со списком всех книг (пагинация + сортировка по году)**</u>

![](./screenshots/006.jpg)

<u>**7. Страница редактирования книги (применяется валидация полей)**</u>

![](./screenshots/007.jpg)

<u>**8. Страница книги (есть возможность назначить книгу читателю из выпадающего списка)**</u>

![](./screenshots/008.jpg)

<u>**9. Страница книги (есть возможность высвободить книгу)**</u>

![](./screenshots/010.jpg)

<u>**10. Страница читателя (присутствуют взятые книги. Красным цветом подсвечиваются просроченные книги)**</u>

![](./screenshots/011.jpg)

<u>**11. Поиск книг (различные результаты)**</u>

![](./screenshots/012.jpg)

![](./screenshots/013.jpg)

![](./screenshots/014.jpg)