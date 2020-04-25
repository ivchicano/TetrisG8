require 'calabash-android/calabash_steps'
require 'calabash-android/operations'

ds = "asd";

Given /^the app starts$/ do
	printf "Starting app..."
end

Given /^the game has started$/ do
	macro "I see \"START GAME\""
	macro "I press \"START GAME\""
	macro "I enter now \"Pedro\" as \"nombreUsuario\""
	macro "I press \"AceptarBtn\""
	macro "I check if exists \"scoreBox\""
	macro "I check if exists \"fichaView\""
end

Given /^I die$/ do
	macro "I check if exists \"buttonDown\""
	macro "I press \"buttonDown\" while exists"
	macro "I see \"TAKE PHOTO\""
    macro "I see \"SKIP\""
end

Then /^I wait (\d+) seconds$/ do |arg1|
	sleep(arg1.to_i) 
end

Then /^I escribir$/ do 
  keyboard_enter_text("hola",options={})
  print ds
end

Then /^I enter now "([^\"]*)" as "([^\"]*)"$/ do |text, field|
  enter_text("* id:'nombreUsuario'", text)
  press_user_action_button
end

Then /^I check if exists "([^\"]*)"$/ do |element|
	wait_for_elements_exist("* id:'#{element}'")
end

Then /^I press "([^\"]*)" while exists$/ do |element|
  while element_exists("* id:'#{element}'")
    tap_when_element_exists("* id:'#{element}'")
  end
end

